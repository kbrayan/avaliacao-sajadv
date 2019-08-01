import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaListaComponent } from './pessoa-lista/pessoa-lista.component';
import { PessoaListaRoutingModule } from './pessoa.routing.module';
import { MaterialModule } from '@app/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TranslateModule } from '@ngx-translate/core';
import { PessoaEditarComponent } from './pessoa-editar/pessoa-editar.component';
import { PessoaService } from './pessoa.service';
import { PessoaCriarComponent } from './pessoa-criar/pessoa-criar.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';

import { NgBrazil } from 'ng-brazil';
import { SharedModule } from '@app/shared';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    FlexLayoutModule,
    MaterialModule,
    PessoaListaRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgBrazil,
    SharedModule
  ],
  declarations: [
    PessoaListaComponent,
    PessoaEditarComponent,
    PessoaCriarComponent
  ],
  providers: [PessoaService]
})
export class PessoaModule {}
