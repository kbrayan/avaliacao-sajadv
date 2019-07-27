import { TestBed } from '@angular/core/testing';

import { PessoaService } from './pessoa.service';

describe('UserListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PessoaService = TestBed.get(PessoaService);
    expect(service).toBeTruthy();
  });
});
