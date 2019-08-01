import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { PessoaService } from '../pessoa.service';
import { Pessoa } from '@app/model/pessoa.model';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ValidateBrService } from 'angular-validate-br';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pessoa-editar',
  templateUrl: './pessoa-editar.component.html',
  styleUrls: ['./pessoa-editar.component.scss']
})
export class PessoaEditarComponent implements OnInit {
  pessoa = new Pessoa(0, '', '', '', '', false);
  pessoaForm: FormGroup;
  submitted = false;
  pessoaId: number;

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.pessoaForm.invalid) {
      return;
    }
  }

  get diagnostic() {
    return JSON.stringify(this.pessoa);
  }
  constructor(
    private PessoaService: PessoaService,
    private formBuilder: FormBuilder,
    private validateBr: ValidateBrService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private _changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit() {
    //this.pessoaForm = new FormGroup({

    //'email': new FormControl(this.pessoa.email, Validators.required),
    //'cpf': new FormControl(this.pessoa.cpf, Validators.required)
    // }); // <-- add custom validator at the FormGroup level
    this.pessoaForm = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.maxLength(140)]],
      email: [
        '',
        [Validators.required, Validators.email, Validators.maxLength(400)]
      ],
      cpf: ['', [Validators.required, this.validateBr.cpf]],
      nascimento: ['', [Validators.required]]
    });

    this.activatedRoute.params.subscribe((params: any) => {
      console.log(params);
      this.pessoaId = params.id;
      this.PessoaService.getPessoa(params.id).subscribe((params: any) => {
        console.log(params);
        this.pessoaForm.setValue({
          nome: params.nome,
          email: params.email,
          cpf: params.cpf,
          nascimento: params.nascimento
        });
        this._changeDetectorRef.detectChanges();
      });
    });
  }

  getPessoa() {
    return JSON.stringify(this.pessoaForm.value);
  }

  editUser() {
    console.log(this.pessoaForm.value);
    var pf = this.pessoaForm.value;

    var pessoaPost = new Pessoa(
      this.pessoaId,
      pf.nome,
      pf.email,
      pf.cpf,
      pf.nascimento,
      false
    );
    console.log('pessoa postada');
    console.log(pessoaPost);
    this.PessoaService.editPessoa(pessoaPost).subscribe(pessoa => {
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
