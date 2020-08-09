package io.nzbee.security.user.location;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nzbee.security.user.User;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);

}
