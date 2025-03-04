package com.project.Glog.service;

import com.project.Glog.domain.Alarm;
import com.project.Glog.domain.User;
import com.project.Glog.dto.AlarmDtos;
import com.project.Glog.repository.AlarmRepository;
import com.project.Glog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    public AlarmDtos getAlarms(Long id) {
        User user = userRepository.findById(id).get();
        List<Alarm> alarms = alarmRepository.findAllByUserOrderByCreatedAtDesc(user);

        if(alarms.size()>20){
            List<Alarm> drop = alarms.subList(0, alarms.size()-20);
            alarms = alarms.subList(alarms.size()-20,alarms.size());
            alarmRepository.deleteAll(drop);
        }

        AlarmDtos alarmDtos = AlarmDtos.of(alarms);

        alarms.forEach(alarm -> alarm.setChecked(true));
        alarmRepository.saveAll(alarms);

        return alarmDtos;
    }
}
