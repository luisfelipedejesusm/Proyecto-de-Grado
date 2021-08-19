import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../_models/user.model';
import { SharedService } from './shared.service';
import { DonationAppointment } from '../_models/donation-apointment.model';
import { Campaign } from '../_models/campaign.model';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  
  
  constructor(private http: HttpClient, private shared: SharedService) { }

  getDonations(): Observable<any>{
    return this.http.get(this.shared.getApiUrl() + 'appointment/donation', {responseType: 'text'});
  }

  saveProfile(user: User): Observable<any> {
    return this.http.patch(this.shared.getApiUrl() + 'user/' + user.id, user);
  }

  getLastDonation(id: Number): Observable<DonationAppointment> {
    return this.http.get<DonationAppointment>(this.shared.getApiUrl() + "appointment/last-appointment/" + id);
  }

  newCampaing(model: Campaign): Observable<any> {
    return this.http.post(this.shared.getApiUrl() + "campaign", model);
  }

  getCampaigns(): Observable<Campaign[]> {
    return this.http.get<Campaign[]>(this.shared.getApiUrl() + "campaign");
  }

  campaignActivation(id: any): Observable<any> {
    return this.http.post(this.shared.getApiUrl() + "campaign/activation/" + id, {});
  }
  
}
