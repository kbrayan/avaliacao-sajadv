import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { extract } from '@app/core';
import { Shell } from '@app/shell/shell.service';
import { PessoaListaComponent } from './pessoa-lista/pessoa-lista.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { PessoaEditarComponent } from './pessoa-editar/pessoa-editar.component';
import { PessoaCriarComponent } from './pessoa-criar/pessoa-criar.component';

const routes: Routes = [
  Shell.childRoutes([
    {
      path: 'pessoa-lista',
      component: PessoaListaComponent,
      data: { title: extract('People List') }
    }
  ]),
  Shell.childRoutes([
    {
      path: 'pessoa-criar',
      component: PessoaCriarComponent,
      data: { title: extract('Register New Person') }
    }
  ]),
  Shell.childRoutes([
    {
      path: 'pessoa-editar',
      children: [
        { path: '', redirectTo: '/', pathMatch: 'full' },
        { path: ':id', component: PessoaEditarComponent, pathMatch: 'full' }
      ]
    }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes), MatDatepickerModule],
  exports: [RouterModule],
  providers: []
})
export class PessoaListaRoutingModule {}
