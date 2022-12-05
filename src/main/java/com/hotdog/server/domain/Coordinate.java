package com.hotdog.server.domain;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
public class Coordinate {
	private static final double SCALE = 1000000.0;

	private static final Double VERTEX_GRADIENT = 1.0;

	private static final Double DISTANCE_TOLERANCE = 0.001;
	/*
	경도 : x
	 */
	private Double latitude;
	/*
	위도 : y
	 */
	private Double longitude;



	public Double getDistanceWith(Coordinate other) {
		final int R = 6371; // Radius of the earth

		double deltaLatitude = Math.toRadians(other.getLatitude() - this.getLatitude());
		double deltaLongitude = Math.toRadians(other.getLongitude() - this.getLongitude());

		double innerFormula = Math.pow(Math.sin(deltaLatitude / 2), 2)
			+ Math.cos(Math.toRadians(this.getLatitude()))
			* Math.cos(Math.toRadians(other.getLatitude()))
			* Math.pow(Math.sin(deltaLongitude / 2), 2);

		return 2 * R * Math.asin(Math.sqrt(innerFormula));
	}

	public Pair<Coordinate, Coordinate> getVertex() {
		final double radius = 5;
		final double bias = calculateBias();
		final long searchStep = 10;
		final long scaledLat = (long)(latitude * SCALE);

		LongPredicate checkDistance = x -> {
			long scaledLon = calculateY(x, bias);

			double lat = x / SCALE;
			double lon = scaledLon / SCALE;

			Coordinate candidate = Coordinate.builder()
				.latitude(lat)
				.longitude(lon)
				.build();

			double distance = this.getDistanceWith(candidate);

			return radius - DISTANCE_TOLERANCE <= distance && distance <= radius + DISTANCE_TOLERANCE;
		};
		long left;
		long right;

		left = (long)(scaledLat - Math.toRadians(radius) * SCALE);
		right = scaledLat;

		long leftVertexX = LongStream.rangeClosed(left, right)
			.filter(i -> i % searchStep == 0)
			.filter(checkDistance)
			.findAny()
			.orElseThrow();

		Coordinate leftVertex = Coordinate.builder()
			.latitude(leftVertexX / SCALE)
			.longitude(calculateY(leftVertexX, bias) / SCALE)
			.build();

		left = scaledLat;
		right = (long)(scaledLat + Math.toRadians(radius) * SCALE);

		long rightVertexX = LongStream.rangeClosed(left, right)
			.filter(i -> i % searchStep == 0)
			.filter(checkDistance)
			.findAny()
			.orElseThrow();

		Coordinate rightVertex = Coordinate.builder()
			.latitude(rightVertexX / SCALE)
			.longitude(calculateY(rightVertexX, bias) / SCALE)
			.build();

		return new ImmutablePair<>(leftVertex, rightVertex);
	}

	private double calculateBias() {
		return longitude - VERTEX_GRADIENT * latitude;
	}

	private long calculateY(long scaledX, double bias) {
		return (long)((VERTEX_GRADIENT * scaledX / SCALE + bias) * SCALE);
	}

}
