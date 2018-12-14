import { TestBed } from '@angular/core/testing';

import { ProjMngService } from './proj-mng.service';

describe('ProjMngService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProjMngService = TestBed.get(ProjMngService);
    expect(service).toBeTruthy();
  });
});
