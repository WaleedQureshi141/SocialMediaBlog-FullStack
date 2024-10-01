import { Component, OnInit } from '@angular/core';
import { Account } from '../account.model';
import { NgForm } from '@angular/forms';
import { AccountService } from '../account.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit
{
  account: Account = 
  {
    username: '',
    password: ''
  }

  constructor(private accountService: AccountService) {}

  saveAccount(accountForm: NgForm): void
  {
    this.accountService.saveAccount(this.account).subscribe
    (
      {
        next: (res: Account) =>
        {
          console.log(res);
          accountForm.reset();
        },
        error: (err: HttpErrorResponse) =>
        {
          console.log(err);
        }
      }
    );
  }

  ngOnInit(): void {}
}
