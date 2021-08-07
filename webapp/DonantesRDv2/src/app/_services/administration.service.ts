import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBank } from '../_models/blood-bank.model';
import { DonationCenter } from '../_models/donation-center.model';
import { User } from '../_models/user.model';
import { SharedService } from './shared.service';

@Injectable({
  providedIn: 'root'
})
export class AdministrationService {

  

  constructor(private http: HttpClient, private shared: SharedService) { }

  createDonationCenter(model: DonationCenter): Observable<any> {
    return this.http.post(this.shared.getApiUrl() + 'donation-center', model);
  }

  getDonationCenters(): Observable<any> {
    return this.http.get<DonationCenter[]>(this.shared.getApiUrl() + 'donation-center');
  }

  updateDonationCenter(model: DonationCenter): Observable<any> {
    return this.http.patch(this.shared.getApiUrl() + 'donation-center/' + model.id, model, this.shared.getHttpOptions());
  }

  deleteDonationCenter(model: DonationCenter): Observable<any> {
    return this.http.delete(this.shared.getApiUrl() + 'donation-center/' + model.id);
  }

  /* ------------------------ Blood Bank ---------------------------*/

  getBloodBanks(): Observable<BloodBank[]>{
    return this.http.get<BloodBank[]>(this.shared.getApiUrl() + 'bloodbank');
  }

  createBloodBank(model: BloodBank): Observable<any> {
    return this.http.post(this.shared.getApiUrl() + 'bloodbank', model);
  }

  updateBloodBank(model: BloodBank): Observable<any> {
    return this.http.patch(this.shared.getApiUrl() + 'bloodbank/' + model.id, model, this.shared.getHttpOptions());
  }

  deleteBloodBank(model: BloodBank): Observable<any> {
    return this.http.delete(this.shared.getApiUrl() + 'bloodbank/' + model.id);
  }

  /* ------------------------- Users ------------------------------- */

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.shared.getApiUrl() + 'user');
  }

  updateUser(user: User): Observable<any>{
    return this.http.patch(this.shared.getApiUrl() + 'user/' + user.id, user);
  }
}
