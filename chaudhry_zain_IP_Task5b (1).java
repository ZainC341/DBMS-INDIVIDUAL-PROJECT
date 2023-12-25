package jsp_azure_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PerformerManagement {
    public static void Main(String[] args) throws FileNotFoundException {
        String url = "jdbc:sqlserver://chau0040-sql-server.database.windows.net;database=cs-dsa-4513-sql-db";
        String user = "chau0040";
        String password = "***";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            int choice = -1;
            while (choice != 4) {
                showMenu();
                choice = getUserChoice();
                switch (choice) {
                    //Cases for project
                    case 1:
                        //System.out.println("Enter a new customer");
                        enterCustomer1(connection);
                        break;
                    case 2:
                    	enterDepartment2(connection);
                    	break;
                    case 3:
                    	enterProcess3(connection);
                    	break;
                    case 4:
                    	enterAssembly4(connection);
                    	break;
                    case 5:
                    	enterAccount5(connection);
                    	break;
                    case 6:
                    	enterJob6(connection);
                    	break;
                    case 7:
                    	jobCompletion7(connection);
                    	break;
                    case 8:
                    	enterTransaction8(connection);
                    	break;
                    case 9:
                    	retrieveCost9(connection);
                    	break;
                    case 10:
                    	retrieveTime10(connection);
                    	break;
                    case 11:
                    	retrieveProcesses11(connection);
                    	break;
                    case 12:
                    	retreiveCustomers12(connection);
                    	break;
                    case 13:
                    	deleteCutJobs(connection);
                    	break;
                    case 14:
                    	changeColor14(connection);
                    	break;
                    case 15:
                    	imported(connection);
                    	break;
                    case 16:
                    	export(connection);
                	case 17:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("WELCOME TO THE JOB ACCOUNTING SYSTEM");
        System.out.println("(1) Enter a new customer");
        System.out.println("(2) Enter a new department");
        System.out.println("(3) Enter a new process-id and its department together with its type and information relevant to the type");
        System.out.println("(4) Enter a new assembly with its customer-name, assembly-details, assembly-id, and date ordered and associate it with one or more processes ");
        System.out.println("(5) Create a new account and associate it with the process, assembly, or department to which it is applicable");
        System.out.println("(6) Enter a new job, given its job-no, assembly-id, process-id, and date the job commenced");
        System.out.println("(7) At the completion of a job, enter the date it completed and the information relevant to the type of job");
        System.out.println("(8) Enter a transaction-no and its sup-cost and update all the costs (details) of the affected accounts by adding sup-cost to their current values of details");
        System.out.println("(9) Retrieve the total cost incurred on an assembly-id");
        System.out.println("(10) Retrieve the total labor time within a department for jobs completed in the department during a given date");
        System.out.println("(11) Retrieve the processes through which a given assembly-id has passed so far (in date-commenced order) and the department responsible for each process");
        System.out.println("(12) Retrieve the customers (in name order) whose category is in a given range");
        System.out.println("(13) Delete all cut-jobs whose job-no is in a given range");
        System.out.println("(14) Change the color of a given paint job");
        System.out.println("(15) Import: enter new customers from a data file until the file is empty (the user must be\r\n"
        		+ "asked to enter the input file name).");
        System.out.println("(16) Export: Retrieve the customers (in name order) whose category is in a given range and\r\n"
        		+ "output them to a data file instead of screen (the user must be asked to enter the output file\r\n"
        		+ "name).");
        System.out.println("(17) Quit");

    }

    private static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }
    
    private static void enterCustomer1(Connection connection) {
    	try (CallableStatement cs = connection.prepareCall("{call enterCustomer1(?, ?, ?)}")) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter customer name: ");
            String name = scanner.next();
            scanner.nextLine();  // Consume the newline character
            
            System.out.print("Enter customer address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter customer category: ");
            int category = scanner.nextInt();

            cs.setString(1, name);
            cs.setString(2, address);
            cs.setInt(3, category);

            cs.execute();

            System.out.println("Customer entered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void enterDepartment2(Connection connection) {
    	try (CallableStatement cs = connection.prepareCall("{call enterDepartment2(?, ?, ?)}")) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Enter process ID: ");
            int process_id = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            
            System.out.print("Enter department number: ");
            int department_number = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            
            System.out.print("Enter department data: ");
            String department_data = scanner.nextLine();
            
            
            cs.setInt(1, process_id);
            cs.setInt(2, department_number);
            cs.setString(3, department_data);

            cs.execute();

            System.out.println("Department entered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void enterProcess3(Connection connection) {
    	Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter process ID: ");
        int process_id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        
        System.out.print("Enter department number: ");
        int department_number = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter process data: ");
        String process_data = scanner.nextLine();
        
        System.out.println("Enter process type: ");
        System.out.println("1. Paint");
        System.out.println("2. Fit");
        System.out.println("3. Cut");
        int type = scanner.nextInt();
        scanner.nextLine();
        
    	try (CallableStatement cs = connection.prepareCall("{call enterProcess3(?, ?, ?)}")) {
            
            cs.setInt(1, process_id);
            cs.setInt(2, department_number);
            cs.setString(3, process_data);

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	if(type == 1) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterPaintProcess3(?, ?, ?)}")) {
	    		System.out.print("Enter paint type: ");
	            String paint_type = scanner.next();
	            scanner.nextLine();  // Consume the newline character
	            
	            System.out.print("Enter painting method: ");
	            String painting_method = scanner.next();
	            scanner.nextLine();
	            
	            cs.setInt(1, process_id);
	            cs.setString(2, paint_type);
	            cs.setString(3, painting_method);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    	}
    	
    	if(type == 2) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterFitProcess3(?, ?)}")) {
	    		System.out.print("Enter fit type: ");
	            String fit_type = scanner.next();
	            scanner.nextLine();  // Consume the newline character
	            
	            cs.setInt(1, process_id);
	            cs.setString(2, fit_type);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    	}
    	
    	if(type == 3) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterCutProcess3(?, ?, ?)}")) {
	    		System.out.print("Enter cutting type: ");
	            String cutting_type = scanner.next();
	            scanner.nextLine();  // Consume the newline character
	            
	            System.out.print("Enter cutting method: ");
	            String cutting_method = scanner.next();
	            scanner.nextLine();  // Consume the newline character
	            
	            
	            cs.setInt(1, process_id);
	            cs.setString(2, cutting_type);
	            cs.setString(3, cutting_method);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
    	}    
    }
    
    private static void enterAssembly4(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String cname = scanner.nextLine();
        
    	System.out.print("Enter assembly ID: ");
        int assembly_id = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        
        System.out.print("Enter assembly details: ");
        String assembly_details = scanner.nextLine();
        
        System.out.print("Enter date ordered(FORMAT: YYYY-MM-DD): ");
        String date_ordered = scanner.next();
        scanner.nextLine();
        
        System.out.print("Enter the number of associated processes: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        
        
        
    	try (CallableStatement cs = connection.prepareCall("{call enterAssembly4(?, ?, ?)}")) {
//            Scanner scanner = new Scanner(System.in);
//            
//            System.out.print("Enter assembly ID: ");
//            int assembly_id = scanner.nextInt();
//            scanner.nextLine();  // Consume the newline character
//            
//            System.out.print("Enter date ordered(FORMAT: YYYY-MM-DD): ");
//            String date_ordered = scanner.next();
//            scanner.nextLine();
//            
//            System.out.print("Enter assembly details: ");
//            String assembly_details = scanner.nextLine();
            

            cs.setInt(1, assembly_id);
            cs.setString(2, date_ordered);
            cs.setString(3, assembly_details);

            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	int i = 0;
        while(i < num) {
        	System.out.print("Enter process ID: ");
        	int process_id = scanner.nextInt();
        	scanner.nextLine();
        	
        	try (CallableStatement cs = connection.prepareCall("{call enterManufacture4(?, ?)}")) {
                
                cs.setInt(1, assembly_id);
                cs.setInt(2, process_id);

                cs.execute();

                //System.out.println("Process entered successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }	
        	
        	i++;
        }
    	
    	try (CallableStatement cs = connection.prepareCall("{call enterOrders4(?, ?)}")) {
            
            cs.setString(1, cname);
            cs.setString(2, assembly_details);

            cs.execute();

            //System.out.println("Process entered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void enterAccount5(Connection connection) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter account number ");
    	int account_number = scanner.nextInt();
    	scanner.nextLine();

    	System.out.print("Enter established-date(FORMAT: YYYY-MM-DD): ");
    	String established_date = scanner.next();
    	scanner.nextLine();
    	
    	try (CallableStatement cs = connection.prepareCall("{call enterAccount5(?, ?)}")) {

          cs.setInt(1, account_number);
          cs.setString(2, established_date);

          cs.execute();

    	} catch (SQLException e) {
          e.printStackTrace();
    	}
    	
    	
    	System.out.println("Enter account type: ");
        System.out.println("1. Assembly");
        System.out.println("2. Department");
        System.out.println("3. Process");
        int type = scanner.nextInt();
        scanner.nextLine();    	
    	
        if(type == 1) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterAssemblyAccount5(?, ?)}")) {
	            double details_1 = 0;
	            
	            cs.setInt(1, account_number);
	            cs.setDouble(2, details_1);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	try (CallableStatement cs = connection.prepareCall("{call enterTracksAssembly5(?, ?)}")) {
	    		
	    		System.out.print("Enter assembly ID: ");
	            int assembly_id = scanner.nextInt();
	            scanner.nextLine();
	            
	    		cs.setInt(1, account_number);
	    		cs.setInt(2, assembly_id);
	    		
	    		cs.execute();
	    	
        	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
        }
        
        if(type == 2) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterDepartmentAccount5(?, ?)}")) {
	            double details_2 = 0;
	            
	            cs.setInt(1, account_number);
	            cs.setDouble(2, details_2);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	try (CallableStatement cs = connection.prepareCall("{call enterTracksDepartment5(?, ?, ?)}")) {
	    		
	    		System.out.print("Enter process ID: ");
	            int process_id = scanner.nextInt();
	            scanner.nextLine();
	            
	            System.out.print("Enter department number: ");
	            int department_number = scanner.nextInt();
	            scanner.nextLine();
	            
	            
	    		cs.setInt(1, account_number);
	    		cs.setInt(2, process_id);
	    		cs.setInt(3, department_number);
	    		
	    		cs.execute();

	    	
        	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}
        }
        
        if(type == 3) {
	    	try (CallableStatement cs = connection.prepareCall("{call enterProcessAccount5(?, ?)}")) {
	            double details_3 = 0;
	            
	            cs.setInt(1, account_number);
	            cs.setDouble(2, details_3);
	
	            cs.execute();
	
	            //System.out.println("Process entered successfully.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    	
	    	try (CallableStatement cs = connection.prepareCall("{call enterTracksProcess5(?, ?)}")) {
	    		
	    		System.out.print("Enter process ID: ");
	            int process_id = scanner.nextInt();
	            scanner.nextLine();
	            
	    		cs.setInt(1, account_number);
	    		cs.setInt(2, process_id);
	    		
	    		cs.execute();

	    	
        	} catch (SQLException e) {
	    		e.printStackTrace();
        	}	
        }
        
    }
    
    private static void enterJob6(Connection connection) {
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.print("Enter job number: ");
    	int job_number = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Enter assembly ID ");
    	int assembly_id = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Enter process ID: ");
    	int process_id = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Enter date job commenced(FORMAT: YYYY-MM-DD): ");
    	String commenced_date = scanner.next();
    	scanner.nextLine();
    	
        try (CallableStatement cs = connection.prepareCall("{call enterJob6(?, ?)}")) {
        	cs.setInt(1, job_number);
        	cs.setString(2, commenced_date);
        	
    		cs.execute();

        
		} catch (SQLException e) {
		    e.printStackTrace();
		}
        
        try (CallableStatement cs = connection.prepareCall("{call updateManufacture6(?, ?, ?)}")) {
        	cs.setInt(1, job_number);
        	cs.setInt(2, assembly_id);
        	cs.setInt(3, process_id);
        	
    		cs.execute();

        
		} catch (SQLException e) {
		    e.printStackTrace();
		}
    }
    
    private static void jobCompletion7(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.print("Enter job number: ");
    	int job_number = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Enter completion date(FORMAT: YYYY-MM-DD): ");
    	String completed_date = scanner.next();
    	scanner.nextLine();
    	
    	System.out.println("Enter job type : ");
        System.out.println("1. Cut");
        System.out.println("2. Paint");
        System.out.println("3. Fit");
        int type = scanner.nextInt();
        scanner.nextLine();
    	
    	try (CallableStatement cs = connection.prepareCall("{call jobCompletion7(?, ?)}")) {
        	cs.setInt(1, job_number);
        	cs.setString(2, completed_date);
        	
    		cs.execute();

        
		} catch (SQLException e) {
		    e.printStackTrace();
		}
    	
    	if(type == 1) {
    		try (CallableStatement cs = connection.prepareCall("{call enterCutJob7(?, ?, ?, ?, ?)}")) {
    			System.out.print("Enter machine types: ");
    	    	String machine_types = scanner.next();
    	    	scanner.nextLine();
    	    	
    	    	System.out.print("Enter time(in mintues) machines used: ");
    	    	String time_machines_used = scanner.next();
    	    	scanner.nextLine();
    	    	
    	    	System.out.print("Enter material used: ");
    	    	String material_used = scanner.next();
    	    	scanner.nextLine();
    	    	
    	    	System.out.print("Enter labor time(in minutes): ");
    	    	double labor_time = scanner.nextDouble();
    	    	scanner.nextLine();
    			
    			
    			cs.setInt(1, job_number);
            	cs.setString(2, machine_types);
            	cs.setString(3, time_machines_used);
            	cs.setString(4, material_used);
            	cs.setDouble(5, labor_time);
            	
        		cs.execute();

            
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    	}
    	
    	if(type == 2) {
    		try (CallableStatement cs = connection.prepareCall("{call enterPaintJob7(?, ?, ?, ?)}")) {
    			System.out.print("Enter color: ");
    	    	String color = scanner.next();
    	    	scanner.nextLine();
    	    	
    	    	System.out.print("Enter volume: ");
    	    	double volume = scanner.nextDouble();
    	    	scanner.nextLine();
    	    	
    	    	System.out.print("Enter labor time(in minutes): ");
    	    	double labor_time = scanner.nextDouble();
    	    	scanner.nextLine();
    			
    			
    			cs.setInt(1, job_number);
            	cs.setString(2, color);
            	cs.setDouble(3, volume);
            	cs.setDouble(4, labor_time);
            	
        		cs.execute();

            
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    	}
    	
    	if(type == 3) {
    		try (CallableStatement cs = connection.prepareCall("{call enterFitJob7(?, ?)}")) {
    			System.out.print("Enter labor time(in minutes): ");
    	    	double labor_time = scanner.nextDouble();
    	    	scanner.nextLine();
    			
    			cs.setInt(1, job_number);
            	cs.setDouble(2, labor_time);
            	
        		cs.execute();

            
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    	}
    }
    
    private static void enterTransaction8(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.print("Enter transaction number: ");
    	int transaction_number = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Enter sup-cost: ");
    	double sup_cost = scanner.nextDouble();
    	scanner.nextLine();
    	
    	System.out.print("Enter job number: ");
    	int job_number = scanner.nextInt();
    	scanner.nextLine();
    	
		try (CallableStatement cs = connection.prepareCall("{call enterTransaction8(?, ?, ?)}")) {
			cs.setInt(1, transaction_number);
			cs.setDouble(2, sup_cost);
			cs.setInt(3, job_number);
			
    		cs.execute();

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		
		try (CallableStatement cs = connection.prepareCall("{call enterRecorded8(?, ?)}")) {
			cs.setInt(1, job_number);
			cs.setInt(2, transaction_number);
			
    		cs.execute();

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    private static void retrieveCost9(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.print("Enter assembly ID: ");
    	int assembly_id = scanner.nextInt();
    	scanner.nextLine();
    	
    	try {
			String query9 = "EXEC retrieveCost9 @assembly_id = '"+assembly_id+"';";
			
			try (final Statement statement = connection.createStatement(); 
					final ResultSet resultSet = statement.executeQuery(query9)) {
				
					System.out.println("Total cost incurred: " + assembly_id); 
					while (resultSet.next()) {
						System.out.println(String.format("%f",
							resultSet.getFloat(1)));
							
						}
					}
		} catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    private static void retrieveTime10(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("Enter department number: ");
		int department_number = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter date(FORMAT: YYYY-MM-DD): ");
		String completed_date = scanner.next();
		scanner.nextLine();
		
		final String query10 = "EXEC retrieveTime10 @department_number = '"+department_number+"', @completed_date = '"+completed_date+"';";
		
		try (Statement statement = connection.createStatement(); 
				ResultSet resultSet = statement.executeQuery(query10)) {
			
				System.out.println("Total labor time(in minutes): ");
				while (resultSet.next()) {
					
					System.out.println(String.format("%s",
						resultSet.getInt(1)));
						
					}
				}
		catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    private static void retrieveProcesses11(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("Enter assembly ID: ");
		int assembly_id = scanner.nextInt();
		scanner.nextLine();
		
		final String query11 = "EXEC retrieveProcesses11 @assembly_id = '"+assembly_id+"';";
		
		try (Statement statement = connection.createStatement(); 
				ResultSet resultSet = statement.executeQuery(query11)) {
			
				System.out.println(String.format("The processes through which Assembly Id %s passed so far:", assembly_id)); 
				while (resultSet.next()) {
					System.out.println("Process ID: ");
					System.out.println(
						resultSet.getString(1)
					);
					
					System.out.println("Department Number: ");
					System.out.println(
						resultSet.getString(2)
					);
					
					System.out.println("Commenced Date: ");
					System.out.println(
						resultSet.getString(3));
					
				}
				
		} catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    private static void retreiveCustomers12(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("From: ");
		int from = scanner.nextInt();
		scanner.nextLine();

		System.out.println("To: ");
		int to = scanner.nextInt();
		scanner.nextLine();

		final String query12 = "EXEC retreiveCustomers12 @from = '"+from+"', @to = '"+to+"';";
		
		try (Statement statement = connection.createStatement(); 
				ResultSet resultSet = statement.executeQuery(query12)) {
			
				while (resultSet.next()) {
					System.out.println(
						resultSet.getString(1)
					);
					System.out.println(
							resultSet.getString(2)
					);
					
					System.out.println("--");
						
				}
				
		} catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    private static void deleteCutJobs(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("From: ");
		int from = scanner.nextInt();
		scanner.nextLine();

		System.out.println("To: ");
		int to = scanner.nextInt();
		scanner.nextLine();
		
		try (CallableStatement cs = connection.prepareCall("{call deleteCutJobs(?, ?)}")) {
			cs.setInt(1, from);
			cs.setInt(2, to);
			
    		cs.execute();

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    private static void changeColor14(Connection connection) {
    	Scanner scanner = new Scanner(System.in);

    	System.out.println("Enter job number: ");
		int job_number = scanner.nextInt();
		scanner.nextLine();

    	System.out.println("Enter color: ");
		String color = scanner.next();
		scanner.nextLine();
		
		
		try (CallableStatement cs = connection.prepareCall("{call changeColor14(?, ?)}")) {
			cs.setInt(1, job_number);
			cs.setString(2, color);
			
    		cs.execute();

    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
		
    }
    
    private static void imported(Connection connection) throws FileNotFoundException {
    	Scanner scanner = new Scanner(System.in);
    	
    	String fileName;
    	System.out.println("Enter file name: ");
		fileName = scanner.next();
		
		
	    File file = new File(fileName);
		
		
		String line;
	    Scanner reader = new Scanner(file);
		while(reader.hasNextLine()) {

			line = reader.nextLine();
			String[] attributes = line.split(",");

			
			
			String name = attributes[0];
			String address = attributes[1];
			int category = Integer.parseInt(attributes[2]);

			
			try (CallableStatement cs = connection.prepareCall("{call enterCustomer1(?, ?, ?)}")) {
				cs.setString(1, name);
				cs.setString(2, address);
				cs.setInt(3, category);

	    		cs.execute();

	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}	
		}
		
		reader.close();
    }
    
    private static void export(Connection connection) {
    	try {
        	Scanner scanner = new Scanner(System.in);

			
			System.out.println("Enter the file name: ");
			String name;
			name = scanner.next();
			
			System.out.println("Enter the lower bound of category.");
			int from;
			from = scanner.nextInt();
			
			System.out.println("Enter the upper bound of category.");
			int to;
			to = scanner.nextInt();
			
			FileWriter writer = new FileWriter(name);
			
			final String query16 = "EXEC retreiveCustomers12 @from = '"+from+"', @to = '"+to+"';";
			
			
			try (final Statement statement = connection.createStatement(); 
					final ResultSet resultSet = statement.executeQuery(query16)) {
				
					
					while (resultSet.next()) {
						
						
						writer.write(resultSet.getString(1) + "," + resultSet.getString(2) + "\n");	
					}
					writer.close();
			} catch (SQLException e) {
				e.getCause().getMessage();
			}
			
			
			} catch (Exception e) {
	    		e.printStackTrace();
			}
    }
    
    
}
