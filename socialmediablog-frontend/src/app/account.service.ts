import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from './account.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService 
{
  constructor(private httpClient: HttpClient) { }

  api = "http://localhost:8080"

  public saveAccount(account: Account): Observable<Account>
  {
    return this.httpClient.post<Account>(`${this.api}/register`, account)
  }
}
