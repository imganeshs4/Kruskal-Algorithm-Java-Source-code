import java.util.*;
import java.io.*;
import java.lang.*;

public class Kruskal
{
  public static int cost[][];
  public static int n;
  
  public static void krush(int cost[][],int n)
  {
    int set[]=new int[10];                                             /* Holds the forest or set of N nodes */
    int mst[][]=new int[10][10];                                       /* Holds the MST nodes */
    int printcost[][]=new int[10][10];

    int min;
    int i,j;
    int u=-1,v=-1;
    int count=0;
    int sum=0,k=0;                                                     /* sum holds total cost of MST and K is the index of MST*/
    
    for(i=0;i<n;i++)
     set[i]=i;                                                         /* Intializes the set */        

    while(count<n-1)
    {
      min=999;
      
      for(i=0;i<n;i++)
      {
        for(j=0;j<n;j++)
        {
          if(cost[i][j]!=0 && cost[i][j]<min)
           { 
              min=cost[i][j];
              u=i;
              v=j;                                                    /* Edges with least cost */
           }
        }
      }
      
      i=find(u,set);
      j=find(v,set);

      if(i!=j)
       { 

         mst[k][0]=u;
         mst[k][1]=v;                                                   /* Selects the edges for Spanning tree */
         k++;
   
         count++;
         sum=sum+min;

         set[v]=u;                                                      /* Takes the union of (u,v) */
       }
      
      printcost[u][v]=cost[u][v];                                       /* To print the cost of MST */

      cost[u][v]=cost[v][u]=999;                                        /* Delete the edge(u,v) from the list to consider next min */

    }

    if(sum>=999)
     System.out.println("\nSpanning tree doesnt exist");
    else
     { 
       System.out.println("\nThe edges selected for MST are:");
       for(i=0;i<n-1;i++)
         System.out.printf("\nEdge%d :  %d----%d   cost = %d",(i+1),mst[i][0],mst[i][1],printcost[mst[i][0]][mst[i][1]]);
       System.out.println("\nMin cost of Spanning tree : "+sum);
     }
    
  }

  public static int find(int x,int set[])
  {
    while(set[x]!=x)
      x=set[x];
                                                                         /* To check the nodes results in circuit or closed path */
    return x;
  }

  
  public static void main(String args[])
  {
    cost=new int[10][10];
    int i, j, n;

    Scanner in=new Scanner(System.in);

    System.out.print("\nEnter the no. of vertices: ");
    n=in.nextInt();
   
    System.out.print("\nEnter the adjacency matrix:\n");
    for(i=0;i < n;i++)
    {
     for(j=0;j < n;j++)
     {
      cost[i][j]=in.nextInt();
     }
    }

    krush(cost,n);
  }

    
}
