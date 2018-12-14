import { TestBed } from '@angular/core/testing';

import { TaskMngService } from './task-mng.service';

describe('TaskMngService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TaskMngService = TestBed.get(TaskMngService);
    expect(service).toBeTruthy();
  });
});
