package com.kikenn.util;

import java.util.*;

public class KahnAlgorithm {
    public static List<Integer> topologicalSort(int numCourses, int[][] prerequisites) {
        List<Integer> result = new ArrayList<>();

        // Initialize in-degree array and adjacency list
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph and in-degree array
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            System.out.println("这里的course是"+course +" "+prerequisiteCourse);
            graph.get(prerequisiteCourse).add(course);
            inDegree[course]++;
        }

        // Perform topological sorting
        Queue<Integer> queue = new LinkedList<>();

        // Add all the nodes with in-degree 0 to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            result.add(currentCourse);

            // Decrement the in-degree of adjacent courses
            for (int adjacentCourse : graph.get(currentCourse)) {
                inDegree[adjacentCourse]--;

                // If the in-degree becomes 0, add it to the queue
                if (inDegree[adjacentCourse] == 0) {
                    queue.offer(adjacentCourse);
                }
            }
        }

        // If not all courses are included in the result, there is a cycle
        if (result.size() != numCourses) {
            return new ArrayList<>();
        }

        return result;
    }

    public static void main(String[] args) {
        int numCourses = 6;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2},{4,3},{5,4},{2,5}};

        List<Integer> sortedOrder = topologicalSort(numCourses, prerequisites);

        if (sortedOrder.isEmpty()) {
            System.out.println("The graph contains a cycle");
        } else {
            System.out.println("Topological order: " + sortedOrder);
        }
    }
}