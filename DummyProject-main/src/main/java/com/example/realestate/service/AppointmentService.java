package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.entity.Appointment;
import com.example.realestate.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment scheduleAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + id));
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment appointment = findById(id);
        appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        appointment.setScheduledTime(updatedAppointment.getScheduledTime());
        appointment.setAgent(updatedAppointment.getAgent());
        appointment.setUser(updatedAppointment.getUser());
        appointment.setFlat(updatedAppointment.getFlat());
        return appointmentRepository.save(appointment);
    }
}
