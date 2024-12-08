import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmCitaComponent } from './confirm-cita.component';

describe('ConfirmCitaComponent', () => {
  let component: ConfirmCitaComponent;
  let fixture: ComponentFixture<ConfirmCitaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmCitaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmCitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
