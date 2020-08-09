package io.nzbee.security.user.devicemetadata;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {

    List<DeviceMetadata> findByUserId(Long userId);
}
