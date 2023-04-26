package commands;

import application.CollectionManager;
import serializers.CollectionSerializer;
import utils.IDGenerator;
import utils.InputReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ExecuteScriptCommand {

    private final CollectionManager collectionManager;
    private static final Set<String> callStack = new LinkedHashSet<>();


    public ExecuteScriptCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(String pathToScript) {
        if (!callStack.contains(pathToScript)) {
            callStack.add(pathToScript);
            // do script
            StringBuilder results = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(pathToScript));
                String[] splitCommand;
                String command;
                while ((command = reader.readLine()) != null) {
                    splitCommand = command.trim().toLowerCase().split(" ", 2);
                    switch (splitCommand[0]) {
                        case "help":
                            results.append(new HelpCommand(this.collectionManager).execute())
                                    .append("\n");
                            break;
                        case "info":
                            results.append(new InfoCommand(this.collectionManager).execute())
                                    .append("\n");
                            break;
                        case "show":
                            results.append(new ShowCommand(this.collectionManager).execute())
                                    .append("\n");
                            break;
                        case "add":
                            results.append(new InsertCommand(this.collectionManager)
                                            .execute(Long.parseLong(splitCommand[1]), InputReader.receiveMovie()))
                                    .append("\n");
                            break;
                        case "update":
                            results.append(new UpdateCommand(this.collectionManager)
                                            .execute(Long.parseLong(splitCommand[1]),
                                                    InputReader.receiveMovie()))
                                    .append("\n");
                            break;
                        case "remove_by_id":
                            results.append(new RemoveKeyCommand(this.collectionManager)
                                    .execute(Long.parseLong(splitCommand[1]))).append("\n");
                            break;
                        case "clear":
                            results.append(new ClearCommand(this.collectionManager).execute())
                                    .append("\n");
                            break;
                        case "save":
                            results.append(this.collectionManager.saveCollection(
                                    CollectionSerializer.serialize(
                                            this.collectionManager.getCollection()))).append("\n");
                            break;
                        case "execute_script":
                            results.append(new ExecuteScriptCommand(this.collectionManager)
                                    .execute(splitCommand[1])).append("\n");
                            break;
                        case "exit":
                            results.append(new ExitCommand().execute()).append("\n");
                        case "filter_greater_than_operator":
                            results.append(new FilterGreaterThanOperatorCommand(this.collectionManager).execute(
                                    InputReader.receivePerson())).append("\n");
                            break;
                        case "remove_greater":
                            results.append(new RemoveGreaterCommand(this.collectionManager)
                                            .execute(InputReader.receiveMovie()))
                                    .append("\n");
                            break;
                        case "remove_lower":
                            results.append(new RemoveLowerCommand(this.collectionManager)
                                            .execute(InputReader.receiveMovie()))
                                    .append("\n");
                            break;
                        case "print_field_descending_operator":
                            results.append(new PrintFieldDescendingOperatorCommand(this.collectionManager)
                                    .execute()).append("\n");
                            break;
                        case "print_unique_total_box_office":
                            results.append(new PrintUniqueTotalBoxOfficeCommand(this.collectionManager).execute())
                                    .append("\n");
                            break;
                        case "replace_if_greater":
                            results.append(new ReplaceIfGreaterCommand(
                                    this.collectionManager)
                                    .execute(Long.parseLong(splitCommand[1]), InputReader.receiveMovie())).append("\n");
                            break;
                        default:
                            reader.readLine();
                            break;
                    }
                }
            } catch (FileNotFoundException fileNotFoundException) {
                return "File with script not found. Check path to script and try again.\n";
            } catch (IOException ioException) {
                return "File reading problems. Try to check file permissions or syntax and try again.\n";
            }
            callStack.remove(pathToScript);
            return results.toString();
        } else {
            return "Ring recursion detected. Script executing aborted.\n";
        }
    }

}