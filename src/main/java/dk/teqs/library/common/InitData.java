package dk.teqs.library.common;

import dk.teqs.library.catalog.model.Work;
import dk.teqs.library.catalog.model.WorkType;
import dk.teqs.library.catalog.repository.WorkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final WorkRepository workRepository;

    public InitData(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public void run(String... args) throws Exception {
        Work work1 = new Work("Java tutorial", WorkType.BOOK,"This book is a detailed explanation of basic java","Abdul Sekerci","Java");
        Work work2 = new Work("C++ tutorial", WorkType.BOOK,"This book is a detailed explanation of basic c++","Abdul Sekerci","C++");
        Work work3 = new Work("Python tutorial", WorkType.BOOK,"This book is a detailed explanation of basic Python","Abdul Sekerci","Python");

        workRepository.save(work1);
        workRepository.save(work2);
        workRepository.save(work3);
    }
}
