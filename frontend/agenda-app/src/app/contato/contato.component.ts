import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContatoService } from '../contato.service';
import { Contato } from './Contato';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  formulario: FormGroup;
  contatos: Contato[] = [];

  constructor(
    private contatoService: ContatoService,
    private FormBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
  }

  configurarFormulario(): void {
    this.formulario = this.FormBuilder.group(
      {
        nome: [null, [Validators.required, Validators.maxLength(150)]],
        email: [null, [Validators.required, Validators.email, Validators.maxLength(150)]]
      }
    )
  }
  onSubmit(): void {

    const contato: Contato = new Contato(this.formulario.get('nome').value, this.formulario.get('email').value)
    this.contatoService.novoContato(contato).subscribe((response) => {
      this.contatos.push(response);

      console.log(this.contatos)
    })

  }


}
