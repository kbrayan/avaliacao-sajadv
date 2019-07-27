import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaCriarComponent } from './pessoa-criar.component';

describe('PessoaCriarComponent', () => {
  let component: PessoaCriarComponent;
  let fixture: ComponentFixture<PessoaCriarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaCriarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaCriarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
