import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../pessoa.service';
import { Pessoa } from '@app/model/pessoa.model';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ValidateBrService } from 'angular-validate-br';

import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pessoa-criar',
  templateUrl: './pessoa-criar.component.html',
  styleUrls: ['./pessoa-criar.component.scss']
})
export class PessoaCriarComponent implements OnInit {
  pessoa = new Pessoa(
    0,
    'chuck',
    'chuck@desafio.com',
    '10283073900',
    '2019-05-06',
    false
  );
  pessoaForm: FormGroup;
  submitted = false;

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.pessoaForm.invalid) {
      return;
    }

    // display form values on success
    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.pessoaForm.value, null, 4));
  }

  get diagnostic() {
    return JSON.stringify(this.pessoa);
  }
  constructor(
    private pessoaService: PessoaService,
    private formBuilder: FormBuilder,
    private validateBr: ValidateBrService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.pessoaForm = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.maxLength(140)]],
      email: [
        '',
        [Validators.required, Validators.email, Validators.maxLength(400)]
      ],
      cpf: ['', [Validators.required, this.validateBr.cpf]],
      nascimento: ['', [Validators.required]]
    });
  }

  getPessoa() {
    return JSON.stringify(this.pessoaForm.value);
  }

  addUser() {
    console.log(this.pessoaForm.value);
    var pf = this.pessoaForm.value;

    var pessoaPost = new Pessoa(
      0,
      pf.nome,
      pf.email,
      pf.cpf,
      pf.nascimento,
      false
    );

    console.log(pessoaPost);
    this.pessoaService.createPessoa(pessoaPost).subscribe(pessoa => {
      if (pessoa) {
        this.router.navigate(['/pessoa-lista']);
      }
    });
  }

  getErrorsCpf() {
    return JSON.stringify(this.f.cpf.errors);
  }

  get f() {
    return this.pessoaForm.controls;
  }

  pin() {
    console.log(this.pessoaForm.controls);
  }
}
