package application;

import commands.*;
import serializers.CollectionSerializer;
import utils.InputReader;

import java.util.Scanner;

public class CommandInvoker {

    private final CollectionManager collectionManager;

    public CommandInvoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String[] splitCommand;
        String command;
        while ((command = scanner.nextLine()) != null) {
            splitCommand = command.trim().toLowerCase().split(" ", 2);
            try {
                switch (splitCommand[0]) {

                    case "help":
                        System.out.println(new HelpCommand(this.collectionManager).execute());
                        break;
                    case "info":
                        System.out.println(new InfoCommand(this.collectionManager).execute());
                        break;
                    case "show":
                        System.out.println(new ShowCommand(this.collectionManager).execute());
                        break;
                    case "insert":
                        System.out.println(new InsertCommand(this.collectionManager)
                                .execute(Long.parseLong(splitCommand[1]), InputReader.receiveMovie()));
                        break;
                    case "update":
                        System.out.println(new UpdateCommand(this.collectionManager)
                                .execute(Long.parseLong(splitCommand[1]),
                                        InputReader.receiveMovie()));
                        break;
                    case "remove_by_id":
                        System.out.println(new RemoveKeyCommand(this.collectionManager)
                                .execute(Long.parseLong(splitCommand[1])));
                        break;
                    case "clear":
                        System.out.println(new ClearCommand(this.collectionManager).execute());
                        break;
                    case "save":
                        System.out.println(this.collectionManager.saveCollection(
                                CollectionSerializer.serialize(
                                        this.collectionManager.getCollection())));
                        break;
                    case "execute_script":
                        System.out.println(new ExecuteScriptCommand(this.collectionManager)
                                .execute(splitCommand[1]));
                        break;
                    case "exit":
                        System.out.println(new ExitCommand().execute());
                    case "filter_greater_than_operator":
                        System.out.println(new FilterGreaterThanOperatorCommand(this.collectionManager).execute(
                                InputReader.receivePerson()));
                        break;
                    case "remove_greater":
                        System.out.println(new RemoveGreaterCommand(this.collectionManager)
                                .execute(InputReader.receiveMovie()));
                        break;
                    case "remove_lower":
                        System.out.println(new RemoveLowerCommand(this.collectionManager)
                                .execute(InputReader.receiveMovie()));
                        break;
                    case "print_field_descending_operator":
                        System.out.println(new PrintFieldDescendingOperatorCommand(this.collectionManager)
                                .execute());
                        break;
                    case "print_unique_total_box_office":
                        System.out.println(new PrintUniqueTotalBoxOfficeCommand(this.collectionManager).execute());
                        break;
                    case "replace_if_greater":
                        System.out.println(new ReplaceIfGreaterCommand(
                                this.collectionManager)
                                .execute(Long.parseLong(splitCommand[1]), InputReader.receiveMovie()));
                        break;
                    default:
                        System.out.println("Unknown command! Type [help] for reading the manual.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect format of command entering. Type [help] for checking the manual.");
            }
        }
        scanner.close();
    }
}
