//import javax.xml.crypto.Data;
//import java.util.ArrayList;
//
//public class Repository {
//
//    public SparseMatrix(ArrayList<String> input) {
//        this.rows = input.size();
//        this.columns = input.get(0).split("").length;
//        this.matrix = new SentinelLL();
//        for(int i = 0; i < Math.max(rows,columns); i++)
//        {
//            matrix.add(new SentinelLL.SentinelNode(i));
//        }
//        Iterator<String> iterator = input.iterator();
//        while (iterator.hasNext()) {
//            for (int i = 0; i < rows; i++) {
//                String[] line = iterator.next().split("");
//                for (int j = 0; j < columns; j++) {
//                    if (line[j].equals("1"))
//                    {
//                        SentinelLL.SentinelNode row = matrix.get(i);
//                        if(row.east == null)
//                            row.setEast(new DataNode<LivingCell>(null,row,new LivingCell(i,j)));
//                        else
//                        {
//                            Node data = row.getEast();
//                            while(data.east != row )
//                                data = data.east;
//                            data.setEast(new DataNode<LivingCell>(null,row, new LivingCell(i,j)));
//                        }
//                        SentinelLL.SentinelNode column = matrix.get(j);
//                        if(column.south == null)
//                            column.setSouth(new Data);
//
//                    }
//                }
//            }
//        }
//
//    }
//
//
//}


//        while (iterator.hasNext()) {
//                for (int i = 0; i < this.rows; i++) {
//        String[] line = iterator.next().split("");
//        List<DataNode> row = new ArrayList<>();
//        for (int j = 0; j < columns; j++) {
//        if (line[j].equals("1"))
//        if(row.isEmpty())
//        row.add(new DataNode(matrix.get(j),matrix.get(i), new LivingCell(i,j)));
//        else {
//        row.add(new DataNode(matrix.get(j),matrix.get(i), new LivingCell(i,j)));
//        row.get(row.size()- 2).
//        }
//        }
//        }
//        }