import { TestBed } from '@angular/core/testing';

import { UtilitieService } from './utilitie-service';

describe('UtilitieService', () => {
  let service: UtilitieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtilitieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
