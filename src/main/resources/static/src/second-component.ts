import {Component} from 'angular2/core'; 
import {NgFor} from 'angular2/common';
import {Todo} from '../src/todo';
import {DataService} from '../src/data-service';

@Component({
  selector: 'second-component',
  directives: [NgFor],
  template: `
    <div> 
      <h2>second component</h2>
      <div *ngFor="#elem of myArray">{{ stringify(elem) }}</div>
    </div>
  `
})
export class SecondComponent {
  private myArray: Array<Todo> = new Array<Todo>();
  
  constructor(private _dataService:DataService) { }
  
  ngOnInit() {
    this._dataService.todos$.subscribe(latestCollection => {
      this.myArray = latestCollection;
    });
    
    this._dataService.load();
    
    var todo1:Todo={
        id:null,
        version:null,
        username:"test1",
        name:"name 1",
        surname:"surname 1",
        email:null,
        photo:null,
        enabled:true,
        authority:"ROLE_ADMIN",
        token:0, 
        password:"changeit"};
    this._dataService.create(todo1);
  }
  
  public stringify(elem:any) {
  	return JSON.stringify(elem);
  }
  
}