# keycloak-theme-additional-info-extension
Provides Password Policy data as well as Realm attribute access to your Keycloak FreeMarker templates.

## Usage

### Password Policy
The Password Policy data is available in the `passwordPolicies` object.
The following properties are available:
- `length`: The minimum length of the password
- `maxLength`: The maximum length of the password
- `digits`: The minimum number of digits required in the password
- `lowerCase`: The minimum number of lowercase characters required in the password
- `upperCase`: The minimum number of uppercase characters required in the password
- `specialChars`: The minimum number of special characters required in the password
- `notUsername`: Whether the password can contain the username
- `notEmail`: Whether the password can contain the email address
- `passwordHistory`: The number of previous passwords that cannot be reused
- `forceExpiredPasswordChange`: The number of days before the password expires

### Realm Attributes
The Realm attributes are available in the `realmAttributes` object.
This is useful
for storing information about your Realm instance that you want to display in your templates.
For example,
you could store the name of your organization in a Realm attribute and display it in your templates.

## Installation

### Manual Installation
1. Download the latest release from the [releases page](https://github.com/jcputney/keycloak-theme-additional-info-extension/releases) and place it in the `$KEYCLOAK_HOME/providers` directory of your Keycloak installation.
2. Run the following command to build the providers into Keycloak:
    ```bash
    $KEYCLOAK_HOME/bin/kc.sh build
    ```
3. Start Keycloak

### Docker Installation
1. Create a Dockerfile with the following contents (or use the `Dockefile.dev` file in this repository):
    ```dockerfile
    FROM maven:3-amazoncorretto-17 AS extensions
    
    WORKDIR /app
    
    COPY pom.xml .
    COPY src src
    
    RUN mvn package
    
    FROM quay.io/keycloak/keycloak:23.0 AS builder
    
    # Install any custom providers here
    
    COPY --from=extensions /app/target/keycloak-theme-additional-info-extension.jar /opt/keycloak/providers/
    
    RUN /opt/keycloak/bin/kc.sh build
    
    FROM quay.io/keycloak/keycloak:23.0
    
    COPY --from=builder /opt/keycloak/ /opt/keycloak/
    
    ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
    ```

2. Build the Docker image:
    ```bash
    docker build -t keycloak-theme-additional-info-extension .
    ```

3. Run the Docker image:
    ```bash
    docker run -p 8080:8080 keycloak-theme-additional-info-extension start-dev
    ```

### Dockerfile COPY Installation
1. Create a Dockerfile with the following contents:
    ```dockerfile
    FROM quay.io/keycloak/keycloak:23.0
    
    COPY --from=ghcr.io/jcputney/keycloak-theme-additional-info-extension:latest /app/keycloak-theme-additional-info-extension.jar /opt/keycloak/providers/
    
    RUN /opt/keycloak/bin/kc.sh build
    
    ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
    ```
2. Build the Docker image:
    ```bash
    docker build -t keycloak-theme-additional-info-extension .
    ```
3. Run the Docker image:
    ```bash
    docker run -p 8080:8080 keycloak-theme-additional-info-extension start-dev
    ```
