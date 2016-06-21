package LogicalLayer.decisionTree;

import java.io.IOException;


public abstract class TreeElem implements TreeFunctions{


    public void visitElem(Tree tree) throws IOException {
        if (question()) {
            this.yes(tree);
        }else {
            this.no(tree);
        }
    }
}
