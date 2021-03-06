import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DonationAppointment } from '../_models/donation-apointment.model';
import { SharedService } from './shared.service';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  

  constructor(private shared: SharedService, private http: HttpClient, private token: TokenStorageService) { }

  createDonationAppointment(appoinmentData: DonationAppointment): Observable<any>{
    return this.http.post(this.shared.getApiUrl() + "public/donation", appoinmentData);
  }

  getMyAppointments(): Observable<DonationAppointment[]> {
    return this.http.get<DonationAppointment[]>(this.shared.getApiUrl() + 'appointment/my-appointments/' + this.token.getUser().id);
  }

  getDcAppointments(): Observable<DonationAppointment[]> {
    return this.http.get<DonationAppointment[]>(this.shared.getApiUrl() + 'donation-center/appointments');
  }

  cancelAppointment(id: any): Observable<any>{
    return this.http.post(this.shared.getApiUrl() + 'appointment/cancel/' + id, {});
  }

}
