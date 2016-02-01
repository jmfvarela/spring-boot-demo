import {Component} from 'angular2/core';
import {NgFor} from 'angular2/common';
import {Todo} from '../src/todo';
import {DataService} from '../src/data-service';

@Component({
  selector: 'first-component',
  directives: [NgFor],
  template: `
    <div>
      <h2>first component</h2>
      <div *ngFor="#elem of myArray">{{ stringify(elem) }}</div>
    </div>
  `
})
export class FirstComponent {
  private myArray: Array<Todo> = new Array<Todo>();

  constructor(private _dataService: DataService) { }
  
  ngOnInit() {
    this._dataService.todos$.subscribe(latestCollection => {
      this.myArray = latestCollection;
    });
    
    this._dataService.load();
  }
  
  public stringify(elem:any) {
  	return JSON.stringify(elem);
  }
  
  
}