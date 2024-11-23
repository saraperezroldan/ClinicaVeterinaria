import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteCitaComponent } from './confirm-delete-cita.component';

describe('ConfirmDeleteCitaComponent', () => {
  let component: ConfirmDeleteCitaComponent;
  let fixture: ComponentFixture<ConfirmDeleteCitaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteCitaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmDeleteCitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
