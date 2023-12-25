DROP PROCEDURE IF EXISTS changeColor14
DROP PROCEDURE IF EXISTS deleteCutJobs
DROP PROCEDURE IF EXISTS retreiveCustomers12
DROP PROCEDURE IF EXISTS retrieveProcesses11
DROP PROCEDURE IF EXISTS retrieveTime10
DROP PROCEDURE IF EXISTS retrieveCost9
DROP PROCEDURE IF EXISTS enterRecorded8
DROP PROCEDURE IF EXISTS enterTransaction8
DROP PROCEDURE IF EXISTS enterFitJob7
DROP PROCEDURE IF EXISTS enterPaintJob7
DROP PROCEDURE IF EXISTS enterCutJob7
DROP PROCEDURE IF EXISTS jobCompletion7
DROP PROCEDURE IF EXISTS updateManufacture6
DROP PROCEDURE IF EXISTS enterJob6
DROP PROCEDURE IF EXISTS enterTracksProcess5
DROP PROCEDURE IF EXISTS enterTracksDepartment5
DROP PROCEDURE IF EXISTS enterTracksAssembly5
DROP PROCEDURE IF EXISTS enterProcessAccount5
DROP PROCEDURE IF EXISTS enterDepartmentAccount5
DROP PROCEDURE IF EXISTS enterAssemblyAccount5
DROP PROCEDURE IF EXISTS enterAccount5
DROP PROCEDURE IF EXISTS enterOrders4
DROP PROCEDURE IF EXISTS enterManufacture4
DROP PROCEDURE IF EXISTS enterAssembly4
DROP PROCEDURE IF EXISTS enterCutProcess3
DROP PROCEDURE IF EXISTS enterFitProcess3
DROP PROCEDURE IF EXISTS enterPaintProcess3
DROP PROCEDURE IF EXISTS enterProcess3
DROP PROCEDURE IF EXISTS enterDepartment2
DROP PROCEDURE IF EXISTS enterCustomer1

--To enter customer
GO
CREATE PROCEDURE enterCustomer1
    @name varchar(255),
    @address varchar(255),
    @category int
    AS
    BEGIN
    INSERT INTO Customer(name, address, category)
    VALUES (@name, @address, @category)
END


--Entering department
GO
CREATE PROCEDURE enterDepartment2
    @process_id INT,
    @department_number INT,
    @department_data VARCHAR(255)
    

    AS
    BEGIN
    INSERT INTO Department(process_id, department_number, department_data)
    VALUES (@process_id, @department_number, @department_data)
    END


--entering process
GO
CREATE PROCEDURE enterProcess3
    @process_id INT,
    @department_number INT,
    @process_data VARCHAR(200)
    
    AS
    BEGIN
    INSERT INTO Process(process_id, department_number, process_data)
    VALUES(@process_id, @department_number, @process_data)
    END

--entering paint process
GO
CREATE PROCEDURE enterPaintProcess3
    @process_id INT,
    @paint_type VARCHAR(50),
    @painting_method VARCHAR(50)

    AS
    BEGIN
    INSERT INTO Paint_process(process_id, paint_type, painting_method)
    VALUES(@process_id, @paint_type, @painting_method)
    END
--entering fit process
GO
CREATE PROCEDURE enterFitProcess3
    @process_id INT,
    @fit_type VARCHAR(50)

    AS
    BEGIN
    INSERT INTO Fit_process(process_id, fit_type)
    VALUES(@process_id, @fit_type)
    END

--entering cut process
GO
CREATE PROCEDURE enterCutProcess3
    @process_id INT,
    @cutting_type VARCHAR(50),
    @cutting_method VARCHAR(50)

    AS
    BEGIN
    INSERT INTO Cut_process(process_id, cutting_type, cutting_method)
    VALUES(@process_id, @cutting_type, @cutting_method)
    END
    

--creating assembly
GO
CREATE PROCEDURE enterAssembly4
    @assembly_id INT,
    @date_ordered DATE,
    @assembly_details VARCHAR(200)

    AS
    BEGIN
    INSERT INTO Assemblies(assembly_id, date_ordered, assembly_details)
    VALUES(@assembly_id, @date_ordered, @assembly_details)
    END


GO
CREATE PROCEDURE enterOrders4
    @cname VARCHAR(255),
    @assembly_id INT

    AS
    BEGIN
    INSERT INTO Orders(cname, assembly_id)
    VALUES(@cname, @assembly_id)
    END

GO
CREATE PROCEDURE enterManufacture4
    @assembly_id INT,
    @process_id INT

    AS
    BEGIN
    INSERT INTO Manufacture(assembly_id, process_id)
    VALUES(@assembly_id, @process_id)
    END

--create account
GO
CREATE PROCEDURE enterAccount5
    @account_number INT,
    @established_date DATE

    AS
    BEGIN
    INSERT INTO Account(account_number, established_date)
    VALUES(@account_number, @established_date)
    END

--create types of accounts
GO
CREATE PROCEDURE enterAssemblyAccount5
    @account_number INT,
    @details_1 FLOAT

    AS
    BEGIN
    INSERT INTO Assembly_account(account_number, details_1)
    VALUES(@account_number, @details_1)
    END

GO
CREATE PROCEDURE enterDepartmentAccount5
    @account_number INT,
    @details_2 FLOAT

    AS
    BEGIN
    INSERT INTO Department_account(account_number, details_2)
    VALUES(@account_number, @details_2)
    END

GO
CREATE PROCEDURE enterProcessAccount5
    @account_number INT,
    @details_3 FLOAT

    AS
    BEGIN
    INSERT INTO Process_account(account_number, details_3)
    VALUES(@account_number, @details_3)
    END

--enters tracks
GO
CREATE PROCEDURE enterTracksAssembly5
    @account_number INT,
    @assembly_id INT

    AS
    BEGIN
    INSERT INTO TracksAssembly(account_number, assembly_id)
    VALUES(@account_number, @assembly_id)
    END

GO
CREATE PROCEDURE enterTracksDepartment5
    @account_number INT,
    @process_id INT,
    @department_number INT
    --@department_data VARCHAR(255)
 
    AS
    BEGIN
    INSERT INTO TracksDepartment(account_number, process_id, department_number)
    VALUES(@account_number, @process_id, @department_number)
    END

GO
CREATE PROCEDURE enterTracksProcess5
    @account_number INT,
    @process_id INT

    AS
    BEGIN
    INSERT INTO TracksProcess(account_number, process_id)
    VALUES(@account_number, 1)
    END

--Query 6
GO
CREATE PROCEDURE enterJob6
    @job_number INT,
    @commenced_date DATE

    AS
    BEGIN
    INSERT INTO Job(job_number, commenced_date)
    VALUES(@job_number, @commenced_date)
    END

GO
CREATE PROCEDURE updateManufacture6
    @job_number INT,
    @assembly_id INT,
    @process_id INT

    AS
    BEGIN
        UPDATE Manufacture
        SET job_number = @job_number
        WHERE assembly_id = @assembly_id
        AND process_id = @process_id;

    END

--Query 7
GO
CREATE PROCEDURE jobCompletion7
    @job_number INT,
    @completed_date DATE

    AS
    BEGIN
    UPDATE Job
    SET completed_date = @completed_date
    WHERE job_number = @job_number

END

GO
CREATE PROCEDURE enterCutJob7
    @job_number INT,
    @machine_types VARCHAR(50),
    @time_machines_used VARCHAR(50),
    @material_used VARCHAR(50),
    @labor_time FLOAT

    AS
    BEGIN
    INSERT INTO Cut_Job(job_number, machine_types, time_machines_used, material_used, labor_time)
    VALUES(@job_number, @machine_types, @time_machines_used, @material_used, @labor_time)
    END

GO
CREATE PROCEDURE enterPaintJob7
    @job_number INT,
    @color VARCHAR(50),
    @volume FLOAT,
    @labor_time FLOAT

    AS
    BEGIN
    INSERT INTO Paint_Job(job_number, color, volume, labor_time)
    VALUES(@job_number, @color, @volume, @labor_time)
    END

GO
CREATE PROCEDURE enterFitJob7
    @job_number INT,
    @labor_time FLOAT

    AS
    BEGIN
    INSERT INTO Paint_Job(job_number,labor_time)
    VALUES(@job_number, @labor_time)
    END

--Query 8
GO
CREATE PROCEDURE enterTransaction8 
    @transaction_number INT,
    @sup_cost FLOAT,
    @job_number INT

    AS
    BEGIN
    INSERT INTO Transactions(transaction_number, sup_cost, account_number)
    VALUES(@transaction_number, @sup_cost, @job_number)
    

     
    BEGIN 
                DECLARE @dep_account INT,
                        @asmb_account INT,
                        @prcs_account INT

                SET @dep_account = (SELECT account_number FROM TracksDepartment,Process, Manufacture
                                    WHERE Manufacture.job_number = @job_number and 
                                            TracksDepartment.department_number = Process.department_number and 
                                                Manufacture.process_id = TracksDepartment.process_id);
                
                SET @asmb_account = (SELECT account_number FROM TracksAssembly, Manufacture, Assemblies
                                    WHERE Manufacture.job_number = @job_number and 
                                    TracksAssembly.assembly_id = Assemblies.assembly_id);
                
                SET @prcs_account = (SELECT account_number FROM Process, TracksProcess, Manufacture
                                    WHERE Manufacture.job_number = @job_number and
                                    TracksProcess.process_id = Process.process_id);

                

    UPDATE Assembly_account 
        SET details_1 = details_1 + @sup_cost
        WHERE account_number = @asmb_account

    UPDATE Department_account
        SET details_2 = details_2 + @sup_cost
        WHERE account_number = @dep_account

    UPDATE Process_Account
        SET details_3 = details_3 + @sup_cost
        WHERE account_number = @prcs_account
    END
END


GO
CREATE PROCEDURE enterRecorded8
    @job_number INT,
    @transaction_number INT

    AS
    BEGIN
    INSERT INTO Recorded(job_number, transaction_number)
    VALUES(@job_number, @transaction_number)
    END

--Query 9
GO
CREATE PROCEDURE retrieveCost9
    @assembly_id INT
    AS
    BEGIN 
        SELECT details_1 from Assembly_account, TracksAssembly
            WHERE TracksAssembly.assembly_id = @assembly_id
            AND Assembly_account.account_number = TracksAssembly.account_number
    END 

--Query 10
GO
CREATE PROCEDURE retrieveTime10
    @department_number INT,
    @completed_date DATE
            
        AS
        BEGIN 
            DECLARE @fit_labr_time   INT,
                    @cut_labr_time   INT,
                    @paint_labr_time INT,
                    @total_labr_time INT;

            SET @fit_labr_time = (SELECT labor_time from Fit_Job, Manufacture  
                                    WHERE Fit_Job.job_number in (
                                        SELECT distinct(job_number) from Job 
                                            WHERE Manufacture.process_id in (SELECT distinct(process_id) FROM Process
                                                                        WHERE Process.department_number = @department_number) and 
                                                                    Job.completed_date = cast(@completed_date As date)
                                    )
                                    
                                )

            SET @cut_labr_time = ( SELECT labor_time from Cut_Job, Manufacture  
                                    WHERE Cut_Job.job_number in (
                                        SELECT distinct(job_number) from Job 
                                            WHERE Manufacture.process_id in (SELECT distinct(process_id) FROM Process
                                                                        WHERE Process.department_number = @department_number) and 
                                                                    Job.completed_date = cast(@completed_date As date)
                                    )
                                    
                                )

            SET @paint_labr_time = ( SELECT labor_time from Paint_Job, Manufacture 
                                    WHERE Paint_Job.job_number in (
                                        SELECT distinct(job_number) from Job 
                                            WHERE Manufacture.process_id in (SELECT distinct(process_id) FROM Process
                                                                        WHERE Process.department_number = @department_number) and 
                                                                    Job.completed_date = cast(@completed_date As date)
                                    )
                                    
                                )

            IF @fit_labr_time IS NULL SET @fit_labr_time = 0
            IF @cut_labr_time IS NULL SET @cut_labr_time = 0
            IF @paint_labr_time IS NULL SET @paint_labr_time = 0


            SET @total_labr_time = @fit_labr_time + @cut_labr_time + @paint_labr_time
            SELECT @total_labr_time

        END 

--Query 11
GO
    CREATE PROCEDURE retrieveProcesses11
        @assembly_id INT


        AS
        BEGIN 
            SELECT Manufacture.process_id, Process.department_number, Job.commenced_date
                FROM Job, Process, Manufacture
                WHERE Manufacture.assembly_id = @assembly_id AND Process.process_id = Manufacture.process_id ORDER BY Job.commenced_date;

        END 

--Query 12
GO
    CREATE PROCEDURE retreiveCustomers12
        @from INT,
        @to INT

        AS
        BEGIN 
           SELECT * FROM Customer
        WHERE category BETWEEN @from AND @to
           ORDER BY name       
        END 

--Query 13
GO
    CREATE PROCEDURE deleteCutJobs
        @from INT,
        @to INT

        AS
        BEGIN 
           DELETE FROM Cut_Job
           WHERE job_number BETWEEN @from AND @to

           DELETE FROM Job
           WHERE job_number BETWEEN @from AND @to
        END 

--Query 14
GO
    CREATE PROCEDURE changeColor14
        @job_number INT,
        @color VARCHAR(50)

        AS
        BEGIN
        UPDATE Paint_Job
        SET color = @color
        WHERE job_number = @job_number
                    
        END 








    

    











