/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.gravitino.cli;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.gravitino.cli.commands.DeleteTable;
import org.apache.gravitino.cli.commands.ListIndexes;
import org.apache.gravitino.cli.commands.ListTables;
import org.apache.gravitino.cli.commands.TableAudit;
import org.apache.gravitino.cli.commands.TableDetails;
import org.apache.gravitino.cli.commands.TableDistribution;
import org.apache.gravitino.cli.commands.TablePartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTableCommands {
  private CommandLine mockCommandLine;
  private Options mockOptions;

  @BeforeEach
  void setUp() {
    mockCommandLine = mock(CommandLine.class);
    mockOptions = mock(Options.class);
  }

  @Test
  void testListTablesCommand() {
    ListTables mockList = mock(ListTables.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(CommandEntities.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema");

    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.LIST));
    doReturn(mockList)
        .when(commandLine)
        .newListTables(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema");
    commandLine.handleCommandLine();
    verify(mockList).handle();
  }

  @Test
  void testTableDetailsCommand() {
    TableDetails mockDetails = mock(TableDetails.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.AUDIT)).thenReturn(false);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DETAILS));
    doReturn(mockDetails)
        .when(commandLine)
        .newTableDetails(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema", "users");
    commandLine.handleCommandLine();
    verify(mockDetails).handle();
  }

  @Test
  void testTableIndexCommand() {
    ListIndexes mockIndex = mock(ListIndexes.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.INDEX)).thenReturn(true);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DETAILS));
    doReturn(mockIndex)
        .when(commandLine)
        .newListIndexes(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema", "users");
    commandLine.handleCommandLine();
    verify(mockIndex).handle();
  }

  @Test
  void testTablePartitionCommand() {
    TablePartition mockPartition = mock(TablePartition.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.PARTITION)).thenReturn(true);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DETAILS));
    doReturn(mockPartition)
        .when(commandLine)
        .newTablePartition(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema", "users");
    commandLine.handleCommandLine();
    verify(mockPartition).handle();
  }

  @Test
  void testTableDistributionCommand() {
    TableDistribution mockDistribution = mock(TableDistribution.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.DISTRIBUTION)).thenReturn(true);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DETAILS));
    doReturn(mockDistribution)
        .when(commandLine)
        .newTableDistribution(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema", "users");
    commandLine.handleCommandLine();
    verify(mockDistribution).handle();
  }

  @Test
  void testTableAuditCommand() {
    TableAudit mockAudit = mock(TableAudit.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.AUDIT)).thenReturn(true);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DETAILS));
    doReturn(mockAudit)
        .when(commandLine)
        .newTableAudit(
            GravitinoCommandLine.DEFAULT_URL, false, "metalake_demo", "catalog", "schema", "users");
    commandLine.handleCommandLine();
    verify(mockAudit).handle();
  }

  @Test
  void testDeleteTableCommand() {
    DeleteTable mockDelete = mock(DeleteTable.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DELETE));
    doReturn(mockDelete)
        .when(commandLine)
        .newDeleteTable(
            GravitinoCommandLine.DEFAULT_URL,
            false,
            false,
            "metalake_demo",
            "catalog",
            "schema",
            "users");
    commandLine.handleCommandLine();
    verify(mockDelete).handle();
  }

  @Test
  void testDeleteTableForceCommand() {
    DeleteTable mockDelete = mock(DeleteTable.class);
    when(mockCommandLine.hasOption(GravitinoOptions.METALAKE)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.METALAKE)).thenReturn("metalake_demo");
    when(mockCommandLine.hasOption(GravitinoOptions.NAME)).thenReturn(true);
    when(mockCommandLine.getOptionValue(GravitinoOptions.NAME)).thenReturn("catalog.schema.users");
    when(mockCommandLine.hasOption(GravitinoOptions.FORCE)).thenReturn(true);
    GravitinoCommandLine commandLine =
        spy(
            new GravitinoCommandLine(
                mockCommandLine, mockOptions, CommandEntities.TABLE, CommandActions.DELETE));
    doReturn(mockDelete)
        .when(commandLine)
        .newDeleteTable(
            GravitinoCommandLine.DEFAULT_URL,
            false,
            true,
            "metalake_demo",
            "catalog",
            "schema",
            "users");
    commandLine.handleCommandLine();
    verify(mockDelete).handle();
  }
}
