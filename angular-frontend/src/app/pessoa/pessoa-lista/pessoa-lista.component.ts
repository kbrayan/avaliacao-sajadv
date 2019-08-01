import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { PessoaService } from '../pessoa.service';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Pessoa } from '@app/model/pessoa.model';
import { I18nService } from '@app/core';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-pessoa-lista',
  templateUrl: './pessoa-lista.component.html',
  styleUrls: ['./pessoa-lista.component.scss']
})
export class PessoaListaComponent implements OnInit {
  pessoaList: Pessoa[];
  count$: Observable<number>;
  count2$: Observable<number>;
  displayedColumns: String[];
  lastSortAction: number;

  nomeFilter: String;
  paginaAtual = 0;
  numeroDePaginas = 0;
  paginas: number[] = [];

  SORT_NOME: number = 1;
  SORT_NASCIMENTO: number = 2;
  SORT_EMAIL: number = 3;

  SORT_ASC: boolean = true;

  filtro = { nome: '', email: '', cpf: '', ordem: 'nome', direcao_asc: true };

  tamanhoPagina = 4;

  carregandoRegistros: boolean;
  carregandoContagem: boolean;

  constructor(
    private PessoaService: PessoaService,
    private changeDetectorRefs: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.displayedColumns = [
      'id',
      'nome',
      'email',
      'cpf',
      'nascimento',
      'delete',
      'edit'
    ];
    this.pesquisarFiltrado();
  }

  pesquisarFiltrado(pagina: number = 1) {
    this.carregandoContagem = true;
    this.carregandoRegistros = true;
    this.paginaAtual = pagina;

    this.PessoaService.countPessoasFiltradas(
      this.filtro.nome,
      this.filtro.email,
      this.filtro.cpf
    )
      .pipe(
        finalize(() => {
          this.carregandoContagem = false;
        })
      )
      .subscribe(count => {
        this.atualizarNumeroDePaginas(count);
      });

    var direcao: string = 'asc';
    if (this.filtro.direcao_asc == false) {
      direcao = 'desc';
    }

    this.PessoaService.getPessoasFiltradas(
      this.paginaAtual - 1,
      this.tamanhoPagina,
      this.filtro.nome,
      this.filtro.email,
      this.filtro.cpf,
      this.filtro.ordem,
      direcao
    )
      .pipe(
        finalize(() => {
          this.carregandoRegistros = false;
        })
      )
      .subscribe(pessoas => {
        this.pessoaList = [...pessoas];
      });
  }

  atualizarNumeroDePaginas(quantidadeRegistros: number) {
    this.numeroDePaginas = Math.ceil(quantidadeRegistros / this.tamanhoPagina);
    this.paginas = Array.from(
      { length: this.numeroDePaginas },
      (v, k) => k + 1
    );
  }

  deletarPessoa(pessoa: any) {
    this.PessoaService.deletePessoa(pessoa.id).subscribe(pessoa => {
      this.pesquisarFiltrado();
    });
  }

  sortNome() {
    this.filtro.ordem = 'nome';
    if (this.lastSortAction != this.SORT_NOME) {
      this.filtro.direcao_asc = this.SORT_ASC;
      this.pesquisarFiltrado();
    } else {
      this.filtro.direcao_asc = !this.filtro.direcao_asc;

      this.pesquisarFiltrado();
    }
    this.lastSortAction = this.SORT_NOME;
  }

  sortNascimento() {
    this.filtro.ordem = 'nascimento';
    if (this.lastSortAction != this.SORT_NASCIMENTO) {
      this.filtro.direcao_asc = this.SORT_ASC;
      this.pesquisarFiltrado();
    } else {
      this.filtro.direcao_asc = !this.filtro.direcao_asc;
      this.pesquisarFiltrado();
    }
    this.lastSortAction = this.SORT_NASCIMENTO;
  }
  sortEmail() {
    this.filtro.ordem = 'email';
    if (this.lastSortAction != this.SORT_EMAIL) {
      this.filtro.direcao_asc = this.SORT_ASC;
      this.pesquisarFiltrado();
    } else {
      this.filtro.direcao_asc = !this.filtro.direcao_asc;

      this.pesquisarFiltrado();
    }
    this.lastSortAction = this.SORT_EMAIL;
  }
}
