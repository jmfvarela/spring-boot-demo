//our root app component
import {Component} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {FirstComponent} from '../src/first-component';
import {SecondComponent} from '../src/second-component';
import {DataService} from '../src/data-service';
import {HTTP_PROVIDERS} from 'angular2/http';

@Component({
  selector: 'root-component',
  directives: [FirstComponent, SecondComponent],
  template: `
    <div>
      <first-component></first-component>
      
      <hr />
      
      <second-component></second-component>
    </div>
  `
})
export class App {
  constructor() {
  
  }
}

bootstrap(App, [DataService, HTTP_PROVIDERS])
  .catch(err => console.error(err));