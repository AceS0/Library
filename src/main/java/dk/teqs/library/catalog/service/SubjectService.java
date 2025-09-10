package dk.teqs.library.catalog.service;

import dk.teqs.library.catalog.dto.DtoMapper;
import dk.teqs.library.catalog.dto.SubjectDto;
import dk.teqs.library.catalog.model.Subject;
import dk.teqs.library.catalog.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectDto createSubject(SubjectDto subjectDto){
        Subject subject = DtoMapper.toEntity(subjectDto);
        subject.setId(null);
        return DtoMapper.toDto(subjectRepository.save(subject));
    }

    public List<SubjectDto> getAllSubjects(){
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (var subject : subjects){
            subjectDtos.add(DtoMapper.toDto(subject));
        }
        return subjectDtos;
    }

    public SubjectDto getSubjectById(Long id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()){
            return DtoMapper.toDto(subject.get());
        }
        throw new RuntimeException("Subject not found with the id: " + id);
    }

    public SubjectDto updateSubject(Long id, SubjectDto subjectDto){
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isPresent()){
            Subject subject = DtoMapper.toEntity(subjectDto);
            Subject updatedSubject = existingSubject.get();
            updatedSubject.setName(subject.getName());
            return DtoMapper.toDto(subjectRepository.save(updatedSubject));
        } else{
            throw new RuntimeException("Subject not found with the id: " + id);
        }
    }

    public void deleteSubject(Long id){
        if (subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
        } else {
            throw new RuntimeException("Subject not found with the id: " + id);
        }
    }

    public List<SubjectDto> searchSubjects(String name){
        List<Subject> subjects = subjectRepository.findByNameContaining(name);
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (var subject : subjects){
            subjectDtos.add(DtoMapper.toDto(subject));
        }
        return subjectDtos;
    }
}
