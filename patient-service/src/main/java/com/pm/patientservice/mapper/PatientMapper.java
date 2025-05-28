package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

// place where conversion happens DTO <=> Model
public class PatientMapper {
    // Model(patient entity) -> DTO(custom fields) -> to display to the client
    public static PatientResponseDTO toDTO(Patient patient){

        PatientResponseDTO patientDTO = new PatientResponseDTO();

        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());     //toString() => LocalDate -> String

        return patientDTO;
    }

    // DTO(custom fields) -> Model(entity) => get input from client & store into db
    // why not previous DTO -> this one has more constraints for client input
    public static Patient toModel(PatientRequestDTO patientRequestDTO){

        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));    // LocalDate.parse() => String -> LocalDate
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));  //

        return patient;
    }
}
