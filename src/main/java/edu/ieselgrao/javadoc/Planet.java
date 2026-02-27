package edu.ieselgrao.javadoc;
import java.time.LocalDate;

/**
 * This class make an Object of type Planet
 */
public class Planet {
    // Constants for error messages
    public static final String INVALID_NAME = "[ERROR] Name cannot be null or empty";
    public static final String INVALID_NUMBER_OF_MOONS = "[ERROR] Number of moons cannot be negative";
    public static final String INVALID_MASS = "[ERROR] Mass cannot be less than 10e23 kg";
    public static final String INVALID_RADIUS = "[ERROR] Radius cannot be less than 500 km";
    public static final String INVALID_GRAVITY = "[ERROR] Gravity cannot be negative or zero";
    public static final String INVALID_LAST_ALBEDO_MEASUREMENT = "[ERROR] Last albedo measurement cannot be null or in the future";
    public static final String INVALID_PLANET_TYPE = "[ERROR] Invalid planet type";

    /**
     * MIN_MASS the minimum mass of the Planet
     * MIN_RADIUS the minimum radius of the Planet
     */
    // Constants for minimum values
    private static final double MIN_MASS = 5.97e22;
    private static final double MIN_RADIUS = 500;

    // Attributes
    private String name;
    private int numberOfMoons;
    private double mass;
    private double radius;
    private double gravity;
    private LocalDate lastAlbedoMeasurement;
    private boolean hasRings;
    private Atmosphere atmosphere;
    private PlanetType type;

    /**
     * The basic constructor of the Planet
     * @param name the name of the planet
     * @param numberOfMoons the number of the moons of the Planet
     * @param mass the mass of the Planet
     * @param radius the radius of the Planet
     * @param gravity the gravity of the Planet
     * @param lastAlbedoMeasurement the last albedo measurement of the Planet
     * @param hasRings if the Planet have rings
     * @param type the type of the class PlanetType
     * The atmosphere is null
     */
    // Basic constructor
    public Planet(String name, int numberOfMoons, double mass, double radius, double gravity, LocalDate lastAlbedoMeasurement, boolean hasRings, PlanetType type) {
        setName(name);
        setNumberOfMoons(numberOfMoons);
        setMass(mass);
        setRadius(radius);
        setGravity(gravity);
        setLastAlbedoMeasurement(lastAlbedoMeasurement);
        setHasRings(hasRings);
        setType(type);

        atmosphere = null;
    }

    /**
     * The detailed constructor with Atmosphere
     * @param name the name of the planet
     * @param numberOfMoons the number of the moons of the Planet
     * @param mass the mass of the Planet
     * @param radius the radius of the Planet
     * @param gravity the gravity of the Planet
     * @param lastAlbedoMeasurement the last albedo measurement of the Planet
     * @param hasRings if the Planet have rings
     * @param type the type of the class PlanetType
     * If the atmosphere is invalid set the Atmosphere to null
     */
    // Detailed constructor with atmosphere
    public Planet(String name, int numberOfMoons, double mass, double radius, double gravity, LocalDate lastAlbedoMeasurement, boolean hasRings, PlanetType type, String composition, LocalDate lastObservation, int airQuality, double pressure, double density, boolean hasClouds) {
        setName(name);
        setNumberOfMoons(numberOfMoons);
        setMass(mass);
        setRadius(radius);
        setGravity(gravity);
        setLastAlbedoMeasurement(lastAlbedoMeasurement);
        setHasRings(hasRings);
        setType(type);

        try {
            setAtmosphere(composition, lastObservation, airQuality, pressure, density, hasClouds);
        } catch (IllegalArgumentException e) {
            this.atmosphere = null;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    /**
     * Set the name of the Planet
     * @param name the name of the Planet
     * @throws IllegalArgumentException if the name is invalid
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    /**
     * Set the numbers of the moons
     * @param numberOfMoons the number of moons have the Planet
     * @throws IllegalArgumentException throws an exception if the numbers of moons is invalid
     */
    public void setNumberOfMoons(int numberOfMoons) {
        if (numberOfMoons < 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_MOONS);
        }
        this.numberOfMoons = numberOfMoons;
    }

    public double getMass() {
        return mass;
    }

    /**
     * Set the mass
     * @param mass the mass of the Planet
     * @throws IllegalArgumentException if the mass is invalid
     */
    public void setMass(double mass) {
        if (mass < MIN_MASS) {
            throw new IllegalArgumentException(INVALID_MASS);
        }
        this.mass = mass;
    }
    public double getRadius() {
        return radius;
    }

    /**
     * Set the radius
     * @param radius the radius of the Planet
     * @throws  IllegalArgumentException if the radius is invalid
     */
    public void setRadius(double radius) {
        if (radius < MIN_RADIUS) {
            throw new IllegalArgumentException(INVALID_RADIUS);
        }
        this.radius = radius;
    }
    public double getGravity() {
        return gravity;
    }

    /**
     * Set the gravity
     * @param gravity the gravity of the Planet
     * @throws IllegalArgumentException if the gravity is invalid
     */
    public void setGravity(double gravity) {
        if (gravity <= 0) {
            throw new IllegalArgumentException(INVALID_GRAVITY);
        }
        this.gravity = gravity;
    }
    public LocalDate getLastAlbedoMeasurement() {
        return lastAlbedoMeasurement;
    }

    /**
     * Set the last albedo measurement
     * @param lastAlbedoMeasurement the last albedo measurement of the Planet
     * @throws IllegalArgumentException if the last albedo measurement is invalid
     */
    public void setLastAlbedoMeasurement(LocalDate lastAlbedoMeasurement) {
        // last albedo measurement is allowed to be today (LocalDate.now())
        if (lastAlbedoMeasurement == null || lastAlbedoMeasurement.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(INVALID_LAST_ALBEDO_MEASUREMENT);
        }
        this.lastAlbedoMeasurement = lastAlbedoMeasurement;
    }
    public boolean hasRings() {
        return hasRings;
    }

    /**
     * Set if the planet have rings
     * @param hasRings if the planet have rings or not, true or false
     */
    public void setHasRings(boolean hasRings) {
        this.hasRings = hasRings;
    }
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    /**
     *
     * @param composition the composition of the Planet
     * @param lastObservation the last observation of the Planet
     * @param airQuality the air quality of the Planet
     * @param pressure the pressure of the Planet
     * @param density the density of the Planet
     * @param hasClouds have clouds or not
     */
    public void setAtmosphere(String composition, LocalDate lastObservation, int airQuality, double pressure, double density, boolean hasClouds) {
        // Can propagate IllegalArgumentException
        atmosphere = new Atmosphere(composition, lastObservation, airQuality, pressure, density, hasClouds);
    }

    public PlanetType getType() {
        return type;
    }

    /**
     * Set the type
     * @param type the type of the class Planet type
     * @throws IllegalArgumentException if the planet type is invalid
     */
    public void setType(PlanetType type) {
        if (type == null) {
            throw new IllegalArgumentException(INVALID_PLANET_TYPE);
        }
        this.type = type;
    }





}
