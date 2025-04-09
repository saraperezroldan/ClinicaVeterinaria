import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmDeleteConsultaComponent } from './confirm-delete-consulta.component';

describe('ConfirmDeleteConsultaComponent', () => {
  let component: ConfirmDeleteConsultaComponent;
  let fixture: ComponentFixture<ConfirmDeleteConsultaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfirmDeleteConsultaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfirmDeleteConsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
