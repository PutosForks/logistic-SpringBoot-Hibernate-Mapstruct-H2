package com.logistic.task.integration;

import com.logistic.task.dto.*;
import com.logistic.task.entity.*;
import com.logistic.task.mapper.*;
import com.logistic.task.service.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Component
public class TestHelper {

    private ClientService clientService;
    private AddressService addressService;
    private AddressMapper addressMapper;
    private CrewService crewService;
    private CrewMapper crewMapper;
    private FlightService flightService;
    private FlightMapper flightMapper;
    private PersonService personService;
    private PersonMapper personMapper;
    private ShiftService shiftService;
    private ShiftMapper shiftMapper;
    private VehicleService vehicleService;
    private VehicleMapper vehicleMapper;

    @Autowired
    public TestHelper(ClientService clientService,
                      AddressService addressService, AddressMapper addressMapper,
                      CrewService crewService, CrewMapper crewMapper, FlightService flightService,
                      FlightMapper flightMapper, PersonService personService,
                      PersonMapper personMapper, ShiftService shiftService, ShiftMapper shiftMapper,
                      VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.clientService = clientService;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.crewService = crewService;
        this.crewMapper = crewMapper;
        this.flightService = flightService;
        this.flightMapper = flightMapper;
        this.personService = personService;
        this.personMapper = personMapper;
        this.shiftService = shiftService;
        this.shiftMapper = shiftMapper;
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    public Client createClient() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("address"));
        Client newClient = new Client("Ginger","+3-000-000",addresses);
        return clientService.saveEntity(newClient);
    }

    public void deleteClient(Client client) {
       clientService.deleteById(client.getId());
    }

    public Address createAddress() {
        Address address = new Address("address");
        AddressDto addressDto = addressMapper.toDto(address);
        AddressDto save = addressService.save(addressDto);
        return addressMapper.toEntity(save);
    }

    public void deleteAddress(Address address) {
        addressService.deleteById(address.getId());
    }

    public Crew createCrew() {
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift("shhift"));
        persons.add(new Person("Vanya","+202020",shifts));
        Crew crew = new Crew(new Date(), persons);
        CrewDto crewDto = crewMapper.toDto(crew);
        CrewDto save = crewService.save(crewDto);
        return crewMapper.toEntity(save);
    }

    public void deleteCrew(Crew crew) {
        crewService.deleteById(crew.getId());
    }


    public Flight createFlight() {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("address"));

        Client newClient = new Client("Ginger","+3-000-000",addresses);
        Vehicle vehicle = new Vehicle(VehicleState.Good,"KO6985FV");
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(newClient);

        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift("shhift"));
        persons.add(new Person("Vanya","+202020",shifts));
        Crew crew = new Crew(new Date(), persons);

        Flight flight = new Flight(clients, vehicle,crew);
        FlightDto flightDto = flightMapper.toDto(flight);
        FlightDto save = flightService.save(flightDto);
        return flightMapper.toEntity(save);
    }

    public void deleteFlight(Flight flight) {
        flightService.deleteById(flight.getId());
    }

    public Person createPerson() {
        ArrayList<Shift> shifts = new ArrayList<>();
        shifts.add(new Shift("shiftForPerson"));
        Person person = new Person("PersonName","+3-96-89544",shifts);
        PersonDto personDto = personMapper.toDto(person);
        PersonDto save = personService.save(personDto);
        return personMapper.toEntity(save);
    }

    public void deletePerson(Person person){
        personService.deleteById(person.getId());
    }


    public Shift createShift() {
        Shift shift = new Shift("stubHealthChange");
        ShiftDto shiftDto = shiftMapper.toDto(shift);
        ShiftDto save = shiftService.save(shiftDto);
        return shiftMapper.toEntity(save);
    }

    public void deleteShift(Shift shift){
        shiftService.deleteById(shift.getId());
    }



    public Vehicle createVehicle() {
      Vehicle vehicle = new Vehicle(VehicleState.Good,"KO6985FV");
        VehicleDto save = vehicleService.save(vehicleMapper.toDto(vehicle));
        return vehicleMapper.toEntity(save);
    }

    public void deleteVehicle(Vehicle vehicle){
        vehicleService.deleteById(vehicle.getId());
    }




    public JSONObject getJsonObjectFromFile(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(new FileReader(getFileFromResources(filePath)));
    }

    public String getJsonFromFile(String filePath) throws IOException, ParseException {
        return getJsonObjectFromFile(filePath).toString();
    }

    public File getFileFromResources(String path) {
        return new File(getClass().getClassLoader().getResource(path).getFile());
    }

}
