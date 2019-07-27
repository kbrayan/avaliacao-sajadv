import { IPessoa } from './IPessoa.model';

export class Pessoa implements IPessoa{
   
    constructor(
        public id:number,
        public nome: string,
        public email: string,
        public cpf: string,
        public nascimento: string,
        public removido: boolean
        
      ) {  }
  }
  