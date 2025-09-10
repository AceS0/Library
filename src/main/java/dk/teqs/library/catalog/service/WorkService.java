package dk.teqs.library.catalog.service;

import dk.teqs.library.catalog.dto.DtoMapper;
import dk.teqs.library.catalog.dto.WorkDto;
import dk.teqs.library.catalog.model.Work;
import dk.teqs.library.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkService {
    private final WorkRepository workRepository;


    public WorkService(WorkRepository workRepository){
        this.workRepository = workRepository;
    }

    public WorkDto createWork(WorkDto workDto){
        Work work = DtoMapper.toEntity(workDto);
        work.setId(null);
        return DtoMapper.toDto(workRepository.save(work));
    }

    public List<WorkDto> getAllWorks(){
        List<Work> works = workRepository.findAll();
        List<WorkDto> workDtos = new ArrayList<>();
        for (var work : works){
            workDtos.add(DtoMapper.toDto(work));
        }
        return workDtos;
    }

    public WorkDto getWorkById(Long id){
        Optional<Work> work = workRepository.findById(id);
        if (work.isPresent()){
            return DtoMapper.toDto(work.get());
        }
        throw new RuntimeException("Work not found with the id: " + id);
    }

    public WorkDto updateWork(Long id, WorkDto workDto){
        Optional<Work> existingWork = workRepository.findById(id);
        if (existingWork.isPresent()){
            Work work = DtoMapper.toEntity(workDto);
            Work updatedWork = existingWork.get();
            updatedWork.setTitle(work.getTitle());
            updatedWork.setWorkType(work.getWorkType());
            updatedWork.setDetails(work.getDetails());
            return DtoMapper.toDto(workRepository.save(updatedWork));
        } else{
            throw new RuntimeException("Work not found with the id: " + id);
        }
    }

    public void deleteWork(Long id){
        if (workRepository.existsById(id)){
            workRepository.deleteById(id);
        } else {
            throw new RuntimeException("Work not found with the id: " + id);
        }
    }

    public List<WorkDto> searchWorks(String title){
        List<Work> works = workRepository.findByTitleContaining(title);
        List<WorkDto> workDtos = new ArrayList<>();
        for (var work : works){
            workDtos.add(DtoMapper.toDto(work));
        }
        return workDtos;
    }
}
