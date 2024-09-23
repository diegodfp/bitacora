package com.diavlo.bitacora.activitychangestatus.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activities.infrastructure.repository.ActivityRepository;
import com.diavlo.bitacora.activitychangestatus.application.ActivityChangeStatuService;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityChangeStatusDTO;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.activitystatuses.infrastructure.repository.ActivityStatusRepository;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ActivityChangeStatusAdapter implements ActivityChangeStatuService{
    //Vamos ha ahcer una rpueba colocamndo o intanciando esta estos metodos en El controlador de Actividad
    // si quito la relacion entre esas tablas pues quiza veria el estado actual y no los que ha tenido puede ser

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ActivityChangeStatusRepository activityChangeStatusRepository;
    @Autowired
    private ActivityStatusRepository activityStatusRepository;

    //El update de Activities va lifado con la creacion del Registro de "ActivityStatusChange"
    


    @Override
    @Transactional
    public List<ActivityStatusChange> findAll() {
        return activityChangeStatusRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<ActivityStatusChange> create(ActivityChangeStatusDTO activityChangeStatusDTO) {
       
        Long Activity_id = activityChangeStatusDTO.getActivity_id();
        Long Changed_by_User = activityChangeStatusDTO.getChanged_by_user_id();
        Long status_id = activityChangeStatusDTO.getStatus_id();
        // Buscar las entidades necesarias

        Optional<Activity> id_Activity_Finded = activityRepository.findById(Activity_id);
        Optional<User> id_User_fined = userRepository.findById(Changed_by_User);
        Optional<ActivityStatus> id_Activity_Status_Finded = activityStatusRepository.findById(status_id);

        // Verificar que todas las entidades estén presentes
        if (id_Activity_Finded.isPresent() && id_User_fined.isPresent() && id_Activity_Status_Finded.isPresent() ) {
            ActivityStatusChange activityStatusChange = new ActivityStatusChange();
            // Aca ya empiezo a mapear los datos al DTO

            activityStatusChange.setActivity(id_Activity_Finded.get());
            activityStatusChange.setChangedByUser(id_User_fined.get());
            activityStatusChange.setActivityStatus(id_Activity_Status_Finded.get());
            activityStatusChange.setTimeCreateUpdate(activityChangeStatusDTO.getTimeCreateUpdate());
            activityStatusChange.setChangeComment(activityChangeStatusDTO.getChange_comment());

            // Guardar la entidad en la base de datos
            //Creo una variable de tipo "ActivityStatusChange" para almaecenar el objeto creado 
            ActivityStatusChange Currenly_Register_Status_Ofactivity =activityChangeStatusRepository.save(activityStatusChange);
             // Retornar el objeto guardado envuelto en un Optional
            return Optional.of(Currenly_Register_Status_Ofactivity);
        }
        // Si esta vacio
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ActivityStatusChange> findById(Long id) {
       return activityChangeStatusRepository.findById(id);
    }

}
