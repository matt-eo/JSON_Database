package client;

import com.beust.jcommander.Parameter;

/**
 * The Argument class uses JCommander to retrieve the command line arguments
 */
public class Arguments {
    @Parameter(names = "-t", description = "type of action")
    public String type;
    @Parameter(names = "-k", description = "key")
    public String key;
    @Parameter(names = "-v", description = "value")
    public String value;
    @Parameter(names = "-in", description = "fileName")
    public String fileName;
}
