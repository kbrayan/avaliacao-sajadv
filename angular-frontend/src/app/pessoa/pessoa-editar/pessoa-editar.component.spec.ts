import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaEditarComponent } from './pessoa-editar.component';

describe('UserComponent', () => {
  let component: PessoaEditarComponent;
  let fixture: ComponentFixture<PessoaEditarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaEditarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
