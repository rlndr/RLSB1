# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
./gradlew build

# Run application (default port 8080)
./gradlew bootRun

# Run all tests
./gradlew test

# Package JAR
./gradlew bootJar
```

## Architecture

Spring Boot REST API (Gradle) with MongoDB persistence and Auth0 JWT security.

**Package root:** `tech.lander`

**Layers:**
- `controller/` — REST endpoints for `EarthquakeController`, `ProductController`, `VehicleController`, `HomeController`
- `service/` — `SecurityConfig` (active OAuth2/JWT config), `AudienceValidator` (custom JWT audience check)
- `config/` — `AppConfig` (MongoDB setup), `CorsConfig`, `SwaggerConfig`
- `domain/` — MongoDB document models: `Product`, `Quake`, `Qgroup`, `Vehicle`
- `repository/` — Two patterns coexist: `ProductRepository` uses `MongoTemplate` directly; `ProductRepoNew`, `EqRepository`, `EqGroupRepository`, `VehicleRepository` use Spring Data `MongoRepository` interfaces. `EqLoadRepository` calls the USGS GeoJSON API to fetch and persist earthquake data.
- `Util/` — `DCUtil` (RSA decryption), request logging/interceptor config

## MongoDB Connection & Encryption

The MongoDB URI in every `application*.properties` file is **RSA-encrypted**. `AppConfig` decrypts it at startup using `DCUtil` and the public key at the path configured by `publicKeyLoc`. The database name is `RLAPP`. Collections: `product`, `quake`, `qgroup`, `vehicle`. Missing or incorrect key files will cause startup failure.

## Security

`tech.lander.service.SecurityConfig` is the active Spring Security config (there is no separate `config/SecurityConfig`). It configures:
- Public: `/api/public`
- Authenticated: `/api/private`
- Scoped: `/api/private-scoped` requires `SCOPE_read:messages`
- Vehicle endpoints require `read:vehicle` authority
- JWT issuer: `https://dev-lander-tech.auth0.com/`
- JWT audience: `http://localhost:8080` (overridden in prod)

`AudienceValidator` performs a custom check on the JWT `aud` claim.

## Configuration Profiles

- `application.properties` — localhost defaults (port 8080)
- `application-prod.properties` — port 9090, different key path (`../keys/publicKey`)
- `application-test.properties` — points to a server DB instance

## External Integration

`EqLoadRepository.fetchLatestEq()` calls the USGS Earthquake API (`https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson`), parses the GeoJSON response, and maps it to `Quake` objects linked to a `Qgroup`.

## Swagger

Available at `http://localhost:8080/swagger-ui.html` when running locally.
