// new vers, coded myself, based on a reinterpretation of the MazeStar algorithm i used in a pac-man style game.

package com.Tankgame.PathMaths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinding {

    public int[][] simplifyMap(int[][] wallMap, int[][] interMap) {
        int[][] simpMap = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 28; j++) {
                if (wallMap[i][j] != 0 || interMap[i][j] != 0) {
                    simpMap[i][j] = 1;
                }
            }
        }

        return simpMap;
    }

    public List<int[]> returnPath(Node currentNode) {
        List<int[]> path = new ArrayList<>();
        Node current = currentNode;
        while (current != null) {
            int[] temp = {current.x, current.y};
            path.add(temp);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private List<int[]> getNeighbors(int[][] map, Node currentNode) {
        // uses currentNode x and y to generate the coordinates of unoccupied adjacent points.
        // treats non-local (0,0) points adjacent to occupied spaces as occupied to avoid the path rect overlapping the walls.

        // {X, X, X}
        // {X, O, X}
        // {X, X, X}

        List<int[]> neighs = new ArrayList<>();
        List<int[]> notNeighs = new ArrayList<>();

        int[] xAxis = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] yAxis = {1, 1, 0, -1, -1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int cheX = currentNode.x + xAxis[i];
            int cheY = currentNode.y + yAxis[i];

            if (map[cheY][cheX] == 0) {
                neighs.add(new int[] {cheX, cheY});
            } else {
                notNeighs.add(new int[] {cheX, cheY});
            }
        }

        for (int[] obj : notNeighs) {
            if (obj[0] == 0) {
                neighs.remove(new int[] {currentNode.x-1, obj[1]});
                neighs.remove(new int[] {currentNode.x+1, obj[1]});
            } else if (obj[1] == 0) {
                neighs.remove(new int[] {obj[0], currentNode.y-1});
                neighs.remove(new int[] {obj[0], currentNode.y+1});
            }
        }

        return neighs;
    }

    public List<int[]> findPath(int[][] map, int[] start, int[] end) {
        Node startNode = new Node(start[0], start[1], true);
        startNode.fCost = startNode.gCost = startNode.hCost = 0;

        Node endNode = new Node(end[0], end[1], true);
        endNode.fCost = endNode.gCost = endNode.hCost = 0;

        PriorityQueue<Node> openList = new PriorityQueue<>();
        List<Node> closedList = new ArrayList<>();

        openList.add(startNode);

        int outerIter = 0;
        int maxIter = (map.length*map[0].length/2);

        while (!openList.isEmpty()) {
            outerIter += 1;

            Node currentNode = openList.poll();
            closedList.add(currentNode);

            if (outerIter > maxIter) {
                // if iterates more than the size of the grid, it returns a path from the CurrentNode.
                return new ArrayList<>();
            }

            if (currentNode.equals(endNode)) {
                return returnPath(currentNode);
            }

            // code to get the open adjacent points. if a space is occupied then the code treats both non-local (0, 0) spaces also as occupied.
            List<int[]> neighbors = getNeighbors(map, currentNode);
            List<Node> nodebors = new ArrayList<>();

            for (int[] neighbor : neighbors) {
                Node temp = new Node(neighbor[0], neighbor[1], true);
                temp.parent = currentNode;

                nodebors.add(temp);
            }

            for (Node node : nodebors) {
                if (closedList.contains(node)) {
                    continue;
                }

                node.gCost = currentNode.gCost + 1;
                node.hCost = (int) Math.pow(node.x - endNode.x, 2) + (int) Math.pow(node.y - endNode.y, 2);
                node.calFCost();

                boolean fng = false;

                for (Node openNode : openList) {
                    int[] childPos = {node.x, node.y};
                    int[] openPos = {openNode.x, openNode.y};

                    if (childPos == openPos && node.gCost > openNode.gCost) {
                        fng = true;
                        break;
                    }
                }

                if (fng) {
                    continue;
                }

                openList.add(node);
            }
        }

        return new ArrayList<>();
    }

}
