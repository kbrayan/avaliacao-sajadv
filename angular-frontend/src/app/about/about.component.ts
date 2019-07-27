import { Component, OnInit } from '@angular/core';
import { environment } from '@env/environment';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {
  version: string | null = environment.version;

  pessoas$: Observable<any>;


  constructor() {}

  ngOnInit() {}

  increment() {
    
    var usuario = {nome:"",cpf:"",email:"",id:0};
    usuario.id = Math.floor(Math.random() * 1000);
    usuario.nome = "Kevin";
    usuario.cpf = "10283073900";
    usuario.email = "kevin.brayan@outlook.com";

  }

}
