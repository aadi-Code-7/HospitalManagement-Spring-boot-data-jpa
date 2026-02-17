INSERT INTO patient(name, gender, birth_Date, email, blood_Group)
VALUES
    ('Aditya Sharma', 'Male', '2004-05-23', 'ss.aadi@gmail.com', 'A_Positive'),
    ('Rahul Kumar', 'Male', '1983-02-03', 'rr.rahul@gmail.com', 'O_Positive'),
    ('Samir Dutta', 'Male', '2001-06-20', 'ss.samir@gmail.com', 'A_Positive'),
    ('Kalyane Thakur', 'Female', '2008-02-08', 'kk.kal@gmail.com', 'AB_Negative');

INSERT INTO doctor(name, specialization, email)
VALUES
    ('Dr. Ram Lal Golcha', 'Opthomology', 'ram202@gmail.com'),
    ('Dr. Sweta Tapariya', 'Cardiology', 'sweta122@gmail.com'),
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com'),
    ('Dr. Harish Chandra', 'Dermatology', 'harsh544@gmail.com');

INSERT INTO appointment(appointment_time, doctor_id, reason, patient_id)
VALUES
    ('2026-02-15 10:30:00', 1, 'General Checkup', 1),
    ('2026-02-15 10:30:00', 2, 'Leg Injury', 2),
    ('2026-02-15 10:30:00', 3, 'Ophthalmology', 2),
    ('2026-02-15 10:30:00', 2, 'Eye Irritation', 3),
    ('2026-02-15 10:30:00', 3, 'General Checkup', 4);