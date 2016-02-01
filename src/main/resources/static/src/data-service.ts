import {Http, Headers} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/map'
import {Injectable} from 'angular2/core';
import {Todo} from '../src/todo';
     
@Injectable()
export class DataService {
    todos$: Observable<Array<Todo>>;
    private _todosObserver: any;
    private _dataStore: {
        todos: Array<Todo>
    };
     
    constructor(private _http: Http) {
        this.todos$ = new Observable(observer => 
            this._todosObserver = observer).share();
     
        this._dataStore = { todos: [] };
    }
         
    load() {
        this._http.get('/users').map(response => response.json()).subscribe(data => {
            this._dataStore.todos = data;
            this._todosObserver.next(this._dataStore.todos);
        }, error => console.log('Could not load todos.'));
    }
     
    create(todo: Todo) {
    	console.log('JMFV: '+JSON.stringify(todo));
    	
	    let headers = new Headers();
	    headers.append('Content-Type', 'application/json');
	    
        this._http.post('/users', JSON.stringify(todo), {headers: headers})
            .map(response => response.json()).subscribe(data => {
            this._dataStore.todos.push(data);   
            this._todosObserver.next(this._dataStore.todos);
        }, error => console.log('Could not create todo.'));
    }
     
    update(todo: Todo) {
        this._http.put(`/users/${todo.id}`, JSON.stringify(todo))
            .map(response => response.json()).subscribe(data => {
            this._dataStore.todos.forEach((todo, i) => {
                if (todo.id === data.id) { this._dataStore.todos[i] = data; }
            });
     
            this._todosObserver.next(this._dataStore.todos);
        }, error => console.log('Could not update todo.'));
    }
     
    delete(todo: Todo) {
        this._http.delete(`/api/todos/${todo.id}`).subscribe(response => {
            this._dataStore.todos.forEach((t, index) => {
                if (t.id === todo.id) { this._dataStore.todos.splice(index, 1); }
            });
     
            this._todosObserver.next(this._dataStore.todos);
        }, error => console.log('Could not delete todo.'));
    }
}