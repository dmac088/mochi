package io.nzbee.security.user.locationtoken;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nzbee.security.user.location.UserLocation;

public interface NewLocationTokenRepository extends JpaRepository<NewLocationToken, Long> {

    NewLocationToken findByToken(String token);

    NewLocationToken findByUserLocation(UserLocation userLocation);

}
