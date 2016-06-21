package LogicalLayer.decisionTree;

import java.io.IOException;


public interface TreeFunctions {

    public boolean question() throws IOException;
    public void visitElem(Tree tree) throws IOException;
    public void yes(Tree tree);
    public void no(Tree tree);
}
