package com.diavlo.bitacora.activitychangestatus.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diavlo.bitacora.activitychangestatus.application.ActivityChangeStatuService;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;
import jakarta.transaction.Transactional;

@Service
public class ActivityChangeStatusAdapter implements ActivityChangeStatuService{
    //Vamos ha ahcer una rpueba colocamndo o intanciando esta estos metodos en El controlador de Actividad
    // si quito la relacion entre esas tablas pues quiza veria el estado actual y no los que ha tenido puede ser

    @Autowired
    private ActivityChangeStatusRepository activityChangeStatusRepository;

    //El update de Activities va lifado con la creacion del Registro de "ActivityStatusChange"
    //Verificar si solo el estado de la actividad cambio
    // Solo va ser creado una vez entocnes el registro de creacion solo va ha ahcer ese por ender ese estado tambien 
    // posterior a estos se crearan los otrsos
    //Necesito paasr el post de Change_Status_history a "Activity" para que se ejecuten al mismo timeempo 
    //En este caso va ser otro Mapping


    @Override
    @Transactional
    public List<ActivityStatusChange> findAll() {
        return activityChangeStatusRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<ActivityStatusChange> findById(Long id) {
       return activityChangeStatusRepository.findById(id);
    }

}
